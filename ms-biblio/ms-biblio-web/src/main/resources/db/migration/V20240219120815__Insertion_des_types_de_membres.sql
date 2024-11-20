DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='types_membres')
      THEN
       INSERT INTO public.types_membres(
	id, libelle, version)
	VALUES 
	(default, 'Membre physique', 1),
	(default, 'Membre groupement', 1),
	(default, 'Membre entreprise', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;