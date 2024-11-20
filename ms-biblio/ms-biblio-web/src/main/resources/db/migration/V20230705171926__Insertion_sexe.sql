/**
 * Author:  SAMIE Pyabalo
 * Created: 5 juil. 2023
 */
DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='sexes')
      THEN
        INSERT INTO public.sexes(
            code, libelle, version)
            VALUES ('M', 'Masculin', 1),
                   ('F', 'Feminin', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;