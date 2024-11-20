DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='secteur_activites')
      THEN
       INSERT INTO public.secteur_activites(
	id, libelle, version)
	VALUES 
	 (default, 'Agro-alimentaire', 1),
         (default, 'Informatique', 1),
	 (default, 'Médécine', 1),
	 (default, 'Banque / Assurance', 1),
         (default, 'Informatique', 1),
	 (default, 'Télécommunication', 1),
	 (default, 'Transport / Logistique', 1),
     	 (default, 'Textile / Habillement / Chaussure', 1),
     	 (default, 'Plastique / Caoutchouc', 1),
     	 (default, 'Machines et équipements / Automobile', 1),
     	 (default, ' Études et conseils', 1),
     	 (default, 'Édition / Communication / Multimédia', 1),
     	 (default, 'Chimie / Parachimie', 1),
     	 (default, 'Bois / Papier / Carton / Imprimerie', 1),
     	 (default, 'BTP / Matériaux de construction', 1),
     	 (default, 'Commerce / Négoce / Distribution', 1),
     	 (default, 'Électronique / Électricité', 1),
     	 (default, 'Industrie pharmaceutique', 1),
     	 (default, 'Métallurgie / Travail du métal', 1),
     	 (default, 'Hôtellerie / restauration', 1),
         (default, 'Bricolage / jardinage / animaux', 1),
     	 (default, 'Services juridiques', 1),
	 (default, 'Enseignement', 1),
     	 (default, 'Energie, eau, assainissement', 1),
	 (default, 'Immobilier, logement', 1),
     	 (default, 'Tourisme, voyage', 1),
     	 (default, 'Culture, loisirs, sport', 1),
     	 (default, 'Produits et services à la personne', 1),
     	 (default, 'Services d''assistance et d''intermédiation', 1),
     	 (default, 'Services d''entretien', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;