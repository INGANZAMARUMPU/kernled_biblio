DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='type_groupements')
      THEN
        INSERT INTO public.type_groupements(id,libelle, version)
            VALUES 
              (default,'Groupe conjoint', 1),
              (default,'Groupe solidaire', 1),
              (default,'Groupe d’intérêt économique', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;