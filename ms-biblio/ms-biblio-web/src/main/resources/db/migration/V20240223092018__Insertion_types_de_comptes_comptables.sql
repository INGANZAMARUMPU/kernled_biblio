DO $$
    BEGIN
      IF EXISTS(SELECT *
        FROM information_schema.columns
        WHERE table_name='types_comptes')
      THEN
        INSERT INTO public.types_comptes(
            id, libelle, version)
            VALUES 
                    (default,'Comptes de capitaux', 1),
                    (default,'Comptes d''immobilisations', 1),
                    (default,'Stocks et en-cours', 1),
                    (default,'Comptes de tiers', 1),
                    (default,'Comptes financiers', 1),
                    (default,'Comptes de charge', 1),
                    (default,'Comptes de produit', 1),
                    (default,'Comptes spéciaux', 1)
    ON CONFLICT DO NOTHING;
    END IF;
END $$;