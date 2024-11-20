package com.mediasofthome.biblio.core.beans;

import com.mediasofthome.krnl.constants.CoreConstants;
import com.mediasofthome.krnl.entities.AppSettings;
import com.mediasofthome.krnl.entities.CustomSettingsFormats;
import com.mediasofthome.krnl.entities.EntitySettings;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.entities.Role;
import com.mediasofthome.krnl.entities.Settings;
import com.mediasofthome.krnl.entities.SystemSettingsFormats;
import com.mediasofthome.krnl.params.FilterParam;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.EntityVariableServiceBeanLocal;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.service.KEntityServiceBeanLocal;
import com.mediasofthome.krnl.service.VariableServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericBean;
import com.mediasofthome.krnl.web.beans.UserSessionBean;
import com.mediasofthome.biblio.core.constants.CommonPermissionConstants;
import com.mediasofthome.biblio.core.entities.Entite;
import com.mediasofthome.biblio.core.entities.EntiteSettings;
import com.mediasofthome.biblio.core.service.EntiteSettingServiceBeanLocal;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Mediasoft
 */
@ViewScoped
@Named
public class EntiteSettingBean extends GenericBean<EntiteSettings, Long> {

    @EJB
    private EntiteSettingServiceBeanLocal entiteSettingService;
    @EJB
    private EntityVariableServiceBeanLocal entityVariableService;
    @EJB
    private KEntityServiceBeanLocal kEntityService;
    @EJB
    private VariableServiceBeanLocal variableService;

    @Inject
    private UserSessionBean userSession;

    private boolean customize = false;
    private boolean truthValue = false;
    private boolean entityDefaultSettings;
    private Map<String, String> locales;
    private List<String> zones;
    private Entite entite;
    private List<EntiteSettings> entiteSettingsList = new ArrayList<>();
    private List<Settings> settingList = new ArrayList<>();
    private EntitySettings systemDefaultEntitySettings;
    private Long settingId;
    private List<KEntity> entitesAutorise = new ArrayList<>();
    private int maxDays;

    @Override
    public void initEntity() {
        super.initEntity();
        this.initLocales();
        this.initZones();
        this.maxDays = this.variableService.getIntValue(CoreConstants.VAR_ENTITY_MAX_DAYS_OPENED_DEFAULT_NUMBER);
    }

    @Override
    public void initUpdate() {
        super.initUpdate();
        this.customize = !this.entity.isUseLocaleTimezoneFormats();
    }

    @Override
    public void initList() {
        super.initList();
//        this.entiteSettingsList = new ArrayList<>();
        this.entitesAutorise = this.kEntityService.getAutirised(userSession.getUser());
        this.defaultList();
        if (this.entiteSettingsList.isEmpty()) {
            this.entityDefaultSettings = false;
        }
    }

    public void defaultList() {
        this.entiteSettingsList.clear();
        this.filtrageDeList();
        if (Objects.nonNull(this.userSession.getUser()) && this.userSession.getEntity() == null && entiteSettingsList.isEmpty()) {
            AppSettings appSet = this.entiteSettingService.getDefaultAppSetings(this.userSession.getEntity());
            this.settingList.add(appSet);
            this.entityDefaultSettings = false;
        } else if (Objects.nonNull(this.userSession.getUser()) && this.userSession.getEntity() == null && !entiteSettingsList.isEmpty()) {
            this.entityDefaultSettings = true;
        } else if (Objects.nonNull(this.userSession.getUser()) && this.userSession.getEntity() != null && entiteSettingsList.isEmpty()) {
            AppSettings appSet = this.entiteSettingService.getDefaultAppSetings(this.userSession.getEntity());
            this.settingList.add(appSet);
            this.entityDefaultSettings = false;
        } else if (Objects.nonNull(this.userSession.getUser()) && this.userSession.getEntity() != null && !entiteSettingsList.isEmpty()) {
            this.entityDefaultSettings = true;
        } else {
            this.entityDefaultSettings = true;
        }
    }

    public void filtrageDeList() {
        if (Objects.nonNull(this.userSession.getUser()) && this.entitesAutorise.isEmpty()
                && !this.userSession.getUser().hasRole(Role.SUPER_ADMIN)) {
            this.entiteSettingsList = this.entiteSettingService.getAll(
                    FilterParams.from(
                            FilterParam.from("entity", this.userSession.getEntity())
                    )
            );
        } else {
            for (KEntity ke : this.entitesAutorise) {
                List<EntiteSettings> autoriseds = this.entiteSettingService.getAll(
                        FilterParams.from(
                                FilterParam.from("entity", ke)
                        )
                );
                this.entiteSettingsList.addAll(autoriseds);
            }
        }
    }

    public void trieDeListe() {
        this.entiteSettingsList = this.entiteSettingService.getAll();
        this.entiteSettingsList = this.entiteSettingService.getAll(
                FilterParams.from(
                        FilterParam.from("entity", this.entite)
                )
        );
    }

    public boolean entiteSettingId(EntiteSettings entiteSetting) {
        this.settingId = this.entityVariableService.getLongValue(entiteSetting.getEntity(), CoreConstants.VAR_SETTINGS_DEFAULT_ID);
        return Objects.equals(this.settingId, entiteSetting.getId());
    }

    public void setDefaultSettings(EntiteSettings entiteSetting) {
        if (Objects.nonNull(entiteSetting) && !this.entityVariableService.getAll(
                FilterParams.from(
                        FilterParam.from("kentity", entiteSetting.getEntity())
                )
        ).isEmpty()) {
            this.settingId = this.entityVariableService.getLongValue(entiteSetting.getEntity(), CoreConstants.VAR_SETTINGS_DEFAULT_ID);
            this.entityVariableService.setValue(entiteSetting.getEntity(), CoreConstants.VAR_SETTINGS_DEFAULT_ID, String.valueOf(entiteSetting.getId()));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Aucune variable d'entité n'a été créer pour l'entité sélectionné",
                            "Vous devez créer une variable d'entité avant"));
        }
    }

    private void initZones() {
        this.zones = new ArrayList<>();
        this.zones.addAll(ZoneId.getAvailableZoneIds());
        Collections.sort(this.zones);
    }

    private void initLocales() {
        this.locales = new HashMap<>();
        List<Locale> list = new ArrayList<>();
        list.addAll(Arrays.asList(Locale.getAvailableLocales()));
        Comparator<Locale> comparator = Comparator
                .comparing((Locale e) -> e.getDisplayLanguage())
                .thenComparing(e -> e.getDisplayCountry());
        list.sort(comparator);
        for (Locale e : list) {
            if (StringUtils.isBlank(e.toString())) {
                continue;
            }
            this.locales.put(this.getLocaleLabel(e), e.toString());
        }
    }

    private String getLocaleLabel(Locale locale) {
        if (StringUtils.isBlank(locale.getDisplayCountry())) {
            return locale.getDisplayLanguage(locale);
        }
        return locale.getDisplayLanguage(locale) + " - " + locale.getDisplayCountry(locale);
    }

    public void customizeChanged() {
        if (this.customize) {
            this.entity.setFormats(new CustomSettingsFormats(this.entity));
        } else {
            this.entity.setFormats(new SystemSettingsFormats(this.entity));
        }
    }

    @Override
    public GenericServiceBeanLocal<EntiteSettings, Long> getService() {
        return this.entiteSettingService;
    }

    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_SETTINGS_ADD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_SETTINGS_EDIT);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_SETTINGS_DELETE);
    }

    @Override
    public boolean canAccessDetails() {
        return this.userService.isPermitted(CommonPermissionConstants.PERM_ENTITE_SETTINGS_DETAILS);
    }

    @Override
    public void initAdd() {
        this.entity = new EntiteSettings();
    }

    public boolean isCustomize() {
        return customize;
    }

    public void setCustomize(boolean customize) {
        this.customize = customize;
    }

    public int getMaxDays() {
        return maxDays;
    }

    public void setMaxDays(int maxDays) {
        this.maxDays = maxDays;
    }

    public Map<String, String> getLocales() {
        return locales;
    }

    public void setLocales(Map<String, String> locales) {
        this.locales = locales;
    }

    public List<String> getZones() {
        return zones;
    }

    public void setZones(List<String> zones) {
        this.zones = zones;
    }

    public Entite getEntite() {
        return entite;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }

    public Long getSettingId() {
        return settingId;
    }

    public List<EntiteSettings> getEntiteSettingsList() {
        return entiteSettingsList;
    }

    public void setEntiteSettingsList(List<EntiteSettings> entiteSettingsList) {
        this.entiteSettingsList = entiteSettingsList;
    }

    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }

    public boolean isTruthValue() {
        return truthValue;
    }

    public void setTruthValue(boolean truthValue) {
        this.truthValue = truthValue;
    }

    public List<Settings> getSettingList() {
        return settingList;
    }

    public void setSettingList(List<Settings> settingList) {
        this.settingList = settingList;
    }

    public EntitySettings getSystemDefaultEntitySettings() {
        return systemDefaultEntitySettings;
    }

    public void setSystemDefaultEntitySettings(EntitySettings systemDefaultEntitySettings) {
        this.systemDefaultEntitySettings = systemDefaultEntitySettings;
    }

    public List<KEntity> getEntitesAutorise() {
        return entitesAutorise;
    }

    public void setEntitesAutorise(List<KEntity> entitesAutorise) {
        this.entitesAutorise = entitesAutorise;
    }

    public boolean isEntityDefaultSettings() {
        return entityDefaultSettings;
    }

    public void setEntityDefaultSettings(boolean entityDefaultSettings) {
        this.entityDefaultSettings = entityDefaultSettings;
    }

}
