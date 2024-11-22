insert into energy_level_type (type) values ('LOW');
insert into energy_level_type (type) values ('MEDIUM');
insert into energy_level_type (type) values ('HIGH');

insert into farm (id, name, panel_count, hectare_size, subscription_date) values ('60a32ec6-7eee-4976-b937-921f7254d528', 'Fazendinha', 10, 20, '2003-01-20');
insert into solar_panel (id, power, cell_quantity, price, voltage) values ('672afe56-3f7d-4eae-a499-140317f1396e', 10, 10, 10.0, 200);
insert into solar_panel (id, power, cell_quantity, price, voltage) values ('572age56-3h7d-4eae-a499-140317f1396e', 10, 10, 10.0, 200);

insert into farm_solar_panel values ('60a32ec6-7eee-4976-b937-921f7254d528', '672afe56-3f7d-4eae-a499-140317f1396e');
insert into farm_solar_panel values ('60a32ec6-7eee-4976-b937-921f7254d528', '572age56-3h7d-4eae-a499-140317f1396e');
