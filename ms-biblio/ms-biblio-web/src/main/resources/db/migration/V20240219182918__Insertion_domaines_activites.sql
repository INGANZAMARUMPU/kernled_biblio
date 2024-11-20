DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='domaine_activites')
      THEN
       INSERT INTO public.domaine_activites(
	id, libelle,version)
	VALUES 
	 (default, 'Commercial',1),
	 (default, 'Industriel',1),
	 (default, 'Artisanal',1),
	 (default, 'Agricol',1),
	 (default, 'Libéral',1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;

