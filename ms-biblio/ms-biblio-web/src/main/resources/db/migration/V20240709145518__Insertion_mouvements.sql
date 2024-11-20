DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='mouvements')
      THEN
        INSERT INTO public.mouvements(
            id, libelle, version)
            VALUES 
                    (default,'Débit et crédit', 1),
                    (default,'Débit', 1),
                    (default,'Crédit', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;