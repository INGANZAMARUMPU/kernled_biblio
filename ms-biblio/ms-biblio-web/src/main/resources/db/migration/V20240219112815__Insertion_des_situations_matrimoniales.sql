/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  SAMA E3SDSTT
 * Created: 19 févr. 2024
 */
DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='situation_matrimoniales')
      THEN
       INSERT INTO public.situation_matrimoniales(
	id, libelle, version)
	VALUES 
	(default, 'Marié(e)', 1),
	(default, 'Célibataire', 1),
	(default, 'Divorcé(e)', 1),
	(default, 'Veuf(ve)', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;