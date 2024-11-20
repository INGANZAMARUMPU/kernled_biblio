/**
 * Author:  SAMIE Pyabalo
 * Created: 17 juin 2024
 */

DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='type_operations')
      THEN
        INSERT INTO public.type_operations(code, libelle, version) 
        VALUES ('ADH', 'Adhésion', 1),
               ('CRE', 'Crédit', 1),
               ('AGIOS', 'Agios', 1),
               ('AN', 'Anouveau', 1),
               ('APS', 'Achat de parts sociales', 1),
               ('CT', 'Commission de tontine', 1),
               ('DAT', 'Dépôt à terme', 1),
               ('DC', 'Déboursement de crédits', 1),
               ('DCC', 'Déboursement de crédits chéques', 1),
               ('DCS', 'Transfert de crédits en souffrance', 1),
               ('DD', 'Décaissement divers', 1),
               ('VSE', 'Dépôt', 1),
               ('PS', 'Part sociale', 1),
               ('RE', 'Retrait ', 1),
               ('VIR', 'Virement d''épargne', 1),
               ('ED', 'Décaissement divers', 1),
               ('VSC', 'Dépôt chèque', 1),
               ('VRC', 'Virement de comptes', 1),
               ('RPS', 'Retrait de part sociale', 1),
               ('DI', 'Dépôt initial', 1),
               ('RC', 'Remboursement de crédit', 1),
               ('ENG', 'Engagement', 1),
               ('FC', 'Frais de crédits', 1),
               ('FL', 'Frais du livret d''épargne', 1),
               ('FE', 'Frais d''entrée', 1),
               ('CC', 'Clôture de comtpes', 1),
               ('RC_CAP', 'Remboursement de crédits capitals', 1),
               ('RC_INT', 'Remboursement de crédits à intérêt', 1),
               ('CON', 'Connexion', 1),
               ('DEC', 'Déconnexion', 1),
               ('CHGE_A', 'Achat', 1),
               ('CHGE_V', 'Vente', 1),
               ('RETRAIT_CAISSE_MOB', 'Retrait du caisse mobile', 1),
               ('SMS_BANKING', 'SMS Banking', 1),
               ('DG', 'Dépôt de garantie', 1),
               ('T_M0B', 'Tontine mobile', 1),
               ('RETRAIT_CASH', 'Retrait cash', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;