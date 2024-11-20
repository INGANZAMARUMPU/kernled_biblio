DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='core_legal_status')
      THEN
        INSERT INTO public.core_legal_status(
            id,code, label, version)
            VALUES 
	      (default, 'SA','Société anonyme', 1),
              (default,'SAS', 'Société par actions simplifiée', 1),
	      (default,'SASU', 'Société par actions simplifiée unipersonnelle', 1),
              (default,'SARL', 'Société à responsabilité limitée', 1),
              (default,'EURL', 'Entreprise unipersonnelle à responsabilité limitée', 1),
	      (default, 'EI','Entrepreneur Individuel', 1),
	      (default, 'SNC','Société en nom collectif', 1),
	      (default, 'SCA','Société en commandite par actions', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;