DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='type_filiations')
      THEN
       INSERT INTO public.type_filiations(
	id, libelle, version)
	VALUES 
	(default, 'Enfant', 1),
	(default, 'Père', 1),
	(default, 'Mère', 1),
	(default, 'Oncle', 1),
	(default, 'Tante', 1),
	(default, 'Cousin(e)', 1),
	(default, 'Neveu', 1),
	(default, 'Niece', 1),
	(default, 'Tuteur', 1),
	(default, 'Tutrice', 1),
	(default, 'Enfant adoptif', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;