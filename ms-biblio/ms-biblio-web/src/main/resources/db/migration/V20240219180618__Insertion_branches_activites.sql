DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='branche_activites')
      THEN
       INSERT INTO public.branche_activites(
	id, libelle, id_secteur_activite ,version)
	VALUES 
	 (default, 'Boulangerie', 15,1),
	 (default, 'Boucherie', 15,1),
	 (default, 'Poissonnerie', 15,1),
	 (default, 'Agences immobilières', 24,1),
         (default, 'Numérique', 2, 1),
	 (default, 'FinTech', 2,1),
	 (default, 'Distribution d''électricité', 16,1),
	 (default, 'Distribution de gaz', 16,1),
	 (default, 'Transport scolaire', 26,1),
	 (default, 'Taxis, véhicules de transport', 26,1),
         (default, 'Débits de boissons (cafés, brasserie)', 19, 1),
         (default, 'Presse', 26, 1),
	 (default, 'Cinéma', 26, 1),
	 (default, 'Notariat', 21, 1),
	 (default, 'Recouvrement de créances', 4, 1),
	 (default, 'Assurance', 4, 1),
	 (default, 'Mutuelles', 4, 1),
	 (default, 'Infirmerie', 3, 1),
	 (default, 'Radiographie', 3, 1),
	 (default, 'Chirurgie', 3, 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;


