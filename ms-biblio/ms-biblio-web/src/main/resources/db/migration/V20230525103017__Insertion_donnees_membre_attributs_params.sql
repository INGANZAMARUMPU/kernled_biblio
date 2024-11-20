/**
 * Author:  SAMIE Pyabalo
 * Created: 25 mai 2023
 */

DO $$
    BEGIN
        IF EXISTS(SELECT *
          FROM information_schema.columns
          WHERE table_name='membres_attributs_parametres')
        THEN
            INSERT INTO public.membres_attributs_parametres(id, libelle, version)
                  VALUES(1,'Numéro du membre',1),
                        (2,'Droit d''entrée',1),
                        (3,'Date de départ',1),
                        (4,'Nombre de part sociale',1),
                        (5,'Numéro de pièce',1),
                        (6,'Date de création pièce',1),
                        (7,'Date d''expiration de la pièce',1),
                        (8,'Lieu d''établissement de la pièce',1),
                        (9,'Date de demande',1),
                        (10,'Observation',1),
                        (12,'Frais d''ouverture',1),
                        (13,'Dépôt',1),
                        (14,'Compteur',1),
                        (15,'Nombre de Signataire exigé',1),
                        (16,'Statut',1),
                        (17,'État',1),
                        (18,'Carte',1),
                        (19,'Email',1),
                        (20,'Téléphone 1',1),
                        (21,'Téléphone 2',1),
                        (22,'Portable',1),
                        (23,'Date de validation',1),
                        (24,'Motif du rejet',1),
                        (25,'Privilège',1),
                        (26,'Départ',1),
                        (27,'Visé',1),
                        (28,'Prelever l''agios',1),
                        (29,'Calcul d''intérêt',1),
                        (30,'Résidence',1),
                        (31,'Pays de délivrance',1),
                        (32,'Nationalité',1),
                        (33,'Type de pièce',1),
                        (34,'Adresse',1),
                        (35,'Catégorie de membre',1),
                        (36,'Part sociale',1),
                        (37,'Type de membre',1),
                        (38,'Domaine d''activité',1),
                        (39,'Entité',1),
                        (40,'Membre',1),
                        (41,'Nom du membre',1),
                        (42,'Prénom du memnre',1),
                        (43,'Nom de la jeune fille',1),
                        (44,'Nom du père',1),
                        (45,'Nom de la mère',1),
                        (46,'Personne à contacter',1),
                        (47,'Date de naissance',1),
                        (48,'Lieu de naissance',1),
                        (49,'Sensibilisateur',1),
                        (50,'Matricule du membre',1),
                        (51,'Sexe',1),
                        (52,'Analphabète',1),
                        (53,'Âge',1),
                        (54,'Nombre d''enfant(s)',1),
                        (55,'Personne chargée',1),
                        (56,'Cogérance',1),
                        (57,'Civilité',1),
                        (58,'Situation matrimoniale',1),
                        (59,'Profession',1),
                        (60,'Photo du membre',1),
                        (61,'Signature du membre',1),
                        (61,'Raison sociale',1),
                        (62,'Date de fondation',1),
                        (63,'Nombre d''hommes',1),
                        (64,'Nombre de femmes',1),
                        (65,'RCCM',1),
                        (66,'Numéro d''installation',1),
                        (67,'Forme juridique',1)

        ON CONFLICT DO NOTHING;
      END IF;
    END $$;