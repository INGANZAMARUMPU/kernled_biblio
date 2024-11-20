DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='core_days')
      THEN
        INSERT INTO public.core_days(
            id, label, version)
            VALUES (1,'Lundi', 1),
                   (2,'Mardi', 1),
                   (3,'Mercredi', 1),
                   (4,'Jeudi', 1),
                   (5,'Vendredi', 1),
                   (6,'Samedi', 1),
                   (7,'Dimanche', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;