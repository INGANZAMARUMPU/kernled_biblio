DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='postes')
      THEN
       INSERT INTO public.postes(
	id, libelle_poste, version)
	VALUES 
	 (default, 'Commerçant(e)', 1),
         (default, 'Dentiste', 1),
	 (default, 'Enseignant(e)', 1),
	 (default, 'Maçon(ne)', 1),
         (default, 'Ménuisier(ère)', 1),
	 (default, 'Mécanicien(ne)', 1),
	 (default, 'Informaticien(ne)', 1),
	 (default, 'Infirmier(ère)', 1),
         (default, 'Pharmacien(ne)', 1),
         (default, 'Sage-femme', 1),
	 (default, 'Aide-soigant', 1),
	 (default, 'Cultivateur', 1),
         (default, 'Eleveur', 1),
	 (default, 'Jardinier', 1),
	 (default, 'Ménagère', 1),
	 (default, 'Agronome', 1),
	 (default, 'Vétérinaire', 1),
	 (default, 'Ingénieur(e)', 1),
	 (default, 'Psychologue', 1),
	 (default, 'Architecte', 1),
	 (default, 'Urbaniste', 1),
	 (default, 'Géologue', 1),
	 (default, 'Sexologue', 1),
	 (default, 'Thérapeute', 1),
	 (default, 'Neurologue', 1),
	 (default, 'Chimiste', 1),
	 (default, 'Arpenteur', 1),
	 (default, 'Technologue', 1),
	 (default, 'Huissier(ère)', 1),
	 (default, 'Avocat(e)', 1),
	 (default, 'Notaire', 1),
	 (default, 'Conseiller(ère)', 1),
	 (default, 'Administrateur', 1),
	 (default, 'Administratrice', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;
