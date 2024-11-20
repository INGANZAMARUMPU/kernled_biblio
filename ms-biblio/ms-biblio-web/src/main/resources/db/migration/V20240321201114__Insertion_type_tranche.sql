/**
 * Author:  SAMIE Pyabalo
 * Created: 21 march. 2024
 */
DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='type_tranches')
      THEN
        INSERT INTO public.type_tranches(code, libelle, version) 
        VALUES ('TMT', 'Tranche montant', 1), ('TDR', 'Tranche Durée', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;