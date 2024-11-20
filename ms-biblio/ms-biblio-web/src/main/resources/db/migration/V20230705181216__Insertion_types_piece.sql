/**
 * Author:  SAMIE Pyabalo
 * Created: 5 juil. 2023
 */
DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='type_pieces')
      THEN
            INSERT INTO public.type_pieces(code, libelle, version)
            VALUES ('CNI', 'Carte nationale d''identité', 1),
                   ('NAT', 'Nationalité', 1),
                   ('CEL', 'Carte d''électeur', 1),
                   ('CSC', 'Carte scolaire', 1),
                   ('CIP', 'Carte d''identité personnelle', 1),
                   ('CS', 'Carte de séjour', 1),
                   ('PAS', 'Passport', 1),
                   ('PDC', 'Permis de conduire', 1),
                   ('AN', 'Acte de naissance', 1),
                   ('AU', 'Autre', 1)
        ON CONFLICT DO NOTHING;
    END IF;
END $$;
