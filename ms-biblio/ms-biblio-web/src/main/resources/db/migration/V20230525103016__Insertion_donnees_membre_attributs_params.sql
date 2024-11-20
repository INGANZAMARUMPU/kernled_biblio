/**
 * Author:  SAMIE Pyabalo
 * Created: 25 mai 2023
 */

DO $$
    BEGIN
        IF EXISTS(SELECT *
          FROM information_schema.columns
          WHERE table_name='membre_attributs')
        THEN
            INSERT INTO public.membre_attributs(
                         id, libelle, visible, requis, description, version)
                  VALUES(1,'Numéro du membre',true,false,' ',1),
                        (2,'Droit d''entrée',true,false,' ',1),
                        (3,'Date de départ',true,false,' ',1),
                        (4,'Nombre de part sociale',true,false,' ',1),
                        (5,'Numéro de pièce',true,false,' ',1),
                        (6,'Date de création pièce',true,false,' ',1),
                        (7,'Date d''expiration de la pièce',true,false,' ',1),
                        (8,'Lieu d''établissement de la pièce',true,false,' ',1),
                        (9,'Date de demande',true,false,' ',1),
                        (10,'Observation',true,false,' ',1),
                        (12,'Frais d''ouverture',true,false,' ',1),
                        (13,'Dépôt',true,false,' ',1),
                        (14,'Compteur',true,false,' ',1),
                        (15,'Nombre de Signataire exigé',true,false,' ',1),
                        (16,'Statut',true,false,' ',1),
                        (17,'État',true,false,' ',1),
                        (18,'Carte',true,false,' ',1),
                        (19,'Email',true,false,' ',1),
                        (20,'Téléphone 1',true,false,' ',1),
                        (21,'Téléphone 2',true,false,' ',1),
                        (22,'Portable',true,false,' ',1),
                        (23,'Date de validation',true,false,' ',1),
                        (24,'Motif du rejet',true,false,' ',1),
                        (25,'Privilège',true,false,' ',1),
                        (26,'Départ',true,false,' ',1),
                        (27,'Visé',true,false,' ',1),
                        (28,'Prelever l''agios',true,false,' ',1),
                        (29,'Calcul d''intérêt',true,false,' ',1),
                        (30,'Résidence',true,false,' ',1),
                        (31,'Pays de délivrance',true,false,' ',1),
                        (32,'Nationalité',true,false,' ',1),
                        (33,'Type de pièce',true,false,' ',1),
                        (34,'Adresse',true,false,' ',1),
                        (35,'Catégorie de membre',true,false,' ',1),
                        (36,'Part sociale',true,false,' ',1),
                        (37,'Type de membre',true,false,' ',1),
                        (38,'Domaine d''activité',true,false,' ',1),
                        (39,'Entité',true,false,' ',1),
                        (40,'Membre',true,false,' ',1),
                        (41,'Nom du membre',true,false,' ',1),
                        (42,'Prénom du memnre',true,false,' ',1),
                        (43,'Nom de la jeune fille',true,false,' ',1),
                        (44,'Nom du père',true,false,' ',1),
                        (45,'Nom de la mère',true,false,' ',1),
                        (46,'Personne à contacter',true,false,' ',1),
                        (47,'Date de naissance',true,false,' ',1),
                        (48,'Lieu de naissance',true,false,' ',1),
                        (49,'Sensibilisateur',true,false,' ',1),
                        (50,'Matricule du membre',true,false,' ',1),
                        (51,'Sexe',true,false,' ',1),
                        (52,'Analphabète',true,false,' ',1),
                        (53,'Âge',true,false,' ',1),
                        (54,'Nombre d''enfant(s)',true,false,' ',1),
                        (55,'Personne chargée',true,false,' ',1),
                        (56,'Cogérance',true,false,' ',1),
                        (57,'Civilité',true,false,' ',1),
                        (58,'Situation matrimoniale',true,false,' ',1),
                        (59,'Profession',true,false,' ',1),
                        (60,'Photo du membre',true,false,' ',1),
                        (61,'Signature du membre',true,false,' ',1),
                        (61,'Raison sociale',true,false,' ',1),
                        (62,'Date de fondation',true,false,' ',1),
                        (63,'Nombre d''hommes',true,false,' ',1),
                        (64,'Nombre de femmes',true,false,' ',1),
                        (65,'RCCM',true,false,' ',1),
                        (66,'Numéro d''installation',true,false,' ',1),
                        (67,'Forme juridique',true,false,' ',1)

        ON CONFLICT DO NOTHING;
      END IF;
    END $$;