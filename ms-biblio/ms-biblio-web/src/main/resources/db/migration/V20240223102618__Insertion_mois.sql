DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='core_months')
      THEN
        INSERT INTO public.core_months(
            id, label, version)
            VALUES (1,'Janvier', 1),
                   (2,'Février', 1),
                   (3,'Mars', 1),
                   (4,'Avril', 1),
                   (5,'Mai', 1),
                   (6,'Juin', 1),
                   (7,'Juillet', 1),
                   (8,'Août', 1),
                   (9,'Septembre', 1),
                   (10,'Octobre', 1),
                   (11,'Novembre', 1),
                   (12,'Décembre', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;