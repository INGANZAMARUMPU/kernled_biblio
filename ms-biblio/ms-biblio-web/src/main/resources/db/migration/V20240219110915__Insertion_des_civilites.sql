
DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='core_civilities')
      THEN
       INSERT INTO public.core_civilities(
	id, code, label, version)
	VALUES 
	(default, 'M.', 'Monsieur', 1),
	(default, 'Mme', 'Madame', 1),
	(default, 'Mlle', 'Mademoiselle', 1),
	(default, 'Mle', 'Monsieur', 1),
	(default, 'Dr', 'Docteur', 1),
	(default, 'Dre', 'Docteure', 1),
	(default, 'S.M', 'Sa Majesté', 1),
	(default, 'S. Exc', 'Son Excellence', 1),
	(default, 'Hon', 'Honorable', 1),
	(default, 'S. Ém', 'Son Éminence', 1),
	(default, 'S. S', 'Sa Sainteté', 1),
	(default, 'R. P', 'Révérend Père', 1),
	(default, 'Mgr', 'Monseigneur', 1),
	(default, 'S. A.', 'Son Altesse', 1),
	(default, 'Pr', 'Professeur', 1),
	(default, 'Me', 'Maître', 1),
	(default, 'Vve', 'Veuve', 1),
	(default, 'R. P', 'Révérend Père', 1),
	(default, 'COL', 'Colonel', 1),
	(default, 'LCL', 'Lieutenant-colonel', 1),
	(default, 'CDT', 'Commandant', 1),
	(default, 'CNE', 'Capitaine', 1),
	(default, 'LTN', 'Lieutenant', 1),
	(default, 'SLT', 'Sous-lieutenant', 1),
	(default, 'ASP','Aspirant', 1),
	(default, 'EO', 'Élèves-officiers', 1),
	(default, 'MAJ', 'Major', 1),
	(default, 'ADJ', 'Adjudant', 1),
	(default, 'SCH', 'Sergent-chef', 1),
	(default, 'SGT', 'Sergent', 1),
	(default, 'CC1', 'Caporal-chef de première classe', 1),
	(default, 'CCH', 'Caporal-chef', 1),
	(default, 'CPL', 'Caporal', 1),
	(default, '1CL', 'Soldat de première classe', 1),
	(default, 'SDT', 'Soldat', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;
