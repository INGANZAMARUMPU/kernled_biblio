/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  mawuli
 * Created: 26 mai 2023
 */
DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='types_acteurs')
      THEN
            INSERT INTO public.types_acteurs(id, libelle, version)
            VALUES (1, 'Bénéficiaire', 1),
                   (2, 'Dirigeant', 1),
                   (3, 'Signataire', 1),
                   (4, 'Membre Groupe', 1),
                   (5, 'Mandataire', 1)
        ON CONFLICT DO NOTHING;
    END IF;
END $$;
