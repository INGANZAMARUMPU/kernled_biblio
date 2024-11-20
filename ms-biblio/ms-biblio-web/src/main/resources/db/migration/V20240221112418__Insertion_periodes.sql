DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='periodes')
      THEN
        INSERT INTO public.periodes(
            id, unite, version)
            VALUES  
                    (default,'Journalier', 1),
                    (default,'Hebdomaire', 1),
                    (default,'Mensuel', 1),
                    (default,'Semestriel', 1),
                    (default,'Trimestriel', 1),
                    (default,'Annuel', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;