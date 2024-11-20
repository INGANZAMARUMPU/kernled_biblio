package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.CritereFraisDaoBean;
import com.mediasofthome.biblio.entities.CategorieMembreCritereFrais;
import com.mediasofthome.biblio.entities.CritereFrais;
import com.mediasofthome.biblio.entities.DeviseCritereFrais;
import com.mediasofthome.biblio.entities.Frais;
import com.mediasofthome.biblio.entities.LocalityCritereFrais;
import com.mediasofthome.biblio.entities.PeriodeCritereFrais;
import com.mediasofthome.biblio.entities.ProfessionCritereFrais;
import com.mediasofthome.biblio.entities.SexeCritereFrais;
import com.mediasofthome.biblio.entities.SousTypeMembreCritereFrais;
import com.mediasofthome.biblio.entities.TypeFrais;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.Locality;
import com.mediasofthome.krnl.entities.LocalityType;
import com.mediasofthome.krnl.entities.Person;
import com.mediasofthome.krnl.entities.User;
import com.mediasofthome.krnl.params.FilterParam;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.params.InFilterParam;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.krnl.service.KEntityServiceBeanLocal;
import com.mediasofthome.krnl.service.LocalityServiceBeanLocal;
import com.mediasofthome.krnl.service.UserServiceBeanLocal;
import com.mediasofthome.biblio.core.entities.TypeOperation;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;

/**
 *
 * @author SAMIE Pyabalo
 * @since Mardi 13 juin 2023 à 11:54
 */
@Stateless
public class CritereFraisServiceBean extends GenericServiceBean<CritereFrais, Long>
        implements CritereFraisServiceBeanLocal {

    @EJB
    private UserServiceBeanLocal userService;
    @EJB
    private CritereFraisDaoBean dao;
    @EJB
    private FraisServiceBeanLocal fraisService;
    @EJB
    private TypeFraisServiceBeanLocal typeFraisService;
    @EJB
    private LocalityServiceBeanLocal localityService;
    @EJB
    private KEntityServiceBeanLocal kEntityService;

    @Override
    public List<Locality> onLocalityTypeChange(LocalityType localityType) {
        List<FilterParam> filterParams = new ArrayList<>();
        filterParams.add(FilterParam.from("localityType", localityType));
        Person person = this.userService.getPerson(this.userService.getCurrent());
        if (person != null && person.getEntity() != null) {
            filterParams.add(FilterParam.from("country", person.getEntity().getAddress()
                    .getLocality().getCountry()));
        }

        return this.localityService.getAll(FilterParams
                .from(filterParams));
    }

    @Override
    public List<Frais> getAllFrais(TypeFrais typeFrais) {
        List<FilterParam> filterParams = new ArrayList<>();
        User user = this.userService.getCurrent();
        Person person = this.userService.getPerson(user);
        filterParams.add(FilterParam.from("typeFrais", typeFrais));

        if (person != null && person.getEntity() != null) {
            filterParams.add(InFilterParam.from("typeFrais.entity",
                    this.kEntityService.getAutirised(user)));
        }
        return this.fraisService.getAll(FilterParams.from(filterParams));
    }

    @Override
    public List<TypeFrais> getAllTypeFrais(TypeOperation typeOperation) {
        List<FilterParam> filterParams = new ArrayList<>();
        User user = this.userService.getCurrent();
        Person person = this.userService.getPerson(user);
        filterParams.add(FilterParam.from("typeOperation", typeOperation));
        if (person != null && person.getEntity() != null) {
            filterParams.add(InFilterParam.from("entity", this.kEntityService.getAutirised(user)));
        }
        return this.typeFraisService.getAll(FilterParams.from(filterParams));
    }

    @Override
    protected GenericDAOBean<CritereFrais, Long> getDAO() {
        return dao;
    }

    @Override
    public void addOne(Frais frais, List<CritereFrais> critereFrais) {
        int ordre = 0;
        frais = this.fraisService.addOneWithFlush(frais);
        for (CritereFrais e : critereFrais) {
            ordre++;
            e.setFrais(frais);
            e.setNumeroOrdre(ordre);
            this.addOneWithFlush(e);
        }
    }

    @Override
    public PeriodeCritereFrais ajouter(CritereFrais critereFrais, PeriodeCritereFrais periodeCritereFrais, Frais frais) {
        if (critereFrais != null) {
            if (critereFrais.isCriterePeriode()) {
                if (periodeCritereFrais == null
                        || periodeCritereFrais.getDebut() == null
                        || periodeCritereFrais.getFin() == null) {
                    Messages.addGlobalError("Veuillez renseigner tous les champs de la période.");
                } else if (periodeCritereFrais.getDebut().isAfter(periodeCritereFrais.getFin())) {
                    Messages.addGlobalError("La date de fin doit être supérieure à la date de début.");
                } else {
                    frais.ajouterCritere(periodeCritereFrais);
                }

            } else {
                frais.ajouterCritere(critereFrais);
            }
        }
        return new PeriodeCritereFrais();
    }

    @Override
    public Long getId(CritereFrais e) {
        return e.getId();
    }

    @Override
    public List<CritereFrais> getAll() {
        return dao.getAll();
    }

}
