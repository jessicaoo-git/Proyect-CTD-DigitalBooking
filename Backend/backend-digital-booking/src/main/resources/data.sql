INSERT INTO titulos_politicas (id,titulo_politica) VALUES (1,'NORMAS_DE_LA_CASA');
INSERT INTO titulos_politicas (id,titulo_politica) VALUES (2,'SALUD_Y_SEGURIDAD');
INSERT INTO titulos_politicas (id,titulo_politica) VALUES (3,'POLITICA_DE_CANCELACION');

INSERT INTO roles (id, rol_nombre) VALUES (1,'ROLE_ADMIN');
INSERT INTO roles (id, rol_nombre) VALUES (2,'ROLE_USER');

INSERT INTO categorias (id, descripcion, titulo, url_imagen) VALUES (1,"Categoria que incluye los hospedajes de tipo Hoteles","Hoteles","https://images6.alphacoders.com/349/thumb-1920-349835.jpg");
INSERT INTO categorias (id, descripcion, titulo, url_imagen) VALUES (2,"Categoria que incluye los hospedajes de tipo Bed and Breakfast","Bed and Breakfast","https://static.hosteltur.com/app/public/uploads/img/articles/2015/08/01/L_5b14fe49e7f2c_breakfast.jpg");
INSERT INTO categorias (id, descripcion, titulo, url_imagen) VALUES (3,"Categoria que incluye los hospedajes de tipo Hostels","Hostels","https://q-xx.bstatic.com/xdata/images/hotel/max1024x768/229095588.jpg?k=90083242c7eea7cfb9db734021fef75f61b70c9d01c0635bb2dc70c95f8cc40d&o=");
INSERT INTO categorias (id, descripcion, titulo, url_imagen) VALUES (4,"Categoria que incluye los hospedajes de tipo Departamentos","Departamentos","https://www.xtrafondos.com/wallpapers/resized/un-departamento-lujoso-2111.jpg?s=large");

INSERT INTO ciudades (id,nombre) VALUES (1,"Buenos Aires");
INSERT INTO ciudades (id,nombre) VALUES (2,"Corrientes");
INSERT INTO ciudades (id,nombre) VALUES (3,"Cordoba");

INSERT INTO caracteristicas (id,titulo,url_imagen) VALUES (1,"Wifi","https://argenlombiaimages.s3.amazonaws.com/iconos/wifi.png");
INSERT INTO caracteristicas (id,titulo,url_imagen) VALUES (2,"Cocina","https://argenlombiaimages.s3.amazonaws.com/iconos/cocina.png");
INSERT INTO caracteristicas (id,titulo,url_imagen) VALUES (3,"Automovil","https://argenlombiaimages.s3.amazonaws.com/iconos/carro.png");
INSERT INTO caracteristicas (id,titulo,url_imagen) VALUES (4,"Aire Acondicionado","https://argenlombiaimages.s3.amazonaws.com/iconos/aire.png");

INSERT INTO productos (id,descripcion,disponible,titulo,categoria_id,ciudad_id) VALUES (1," Está situado solo unas calles de la avenida Alvear, de la avenida
        quientana, del parque San Martín y el distrito de Recoleta. En las
        inmediaciones también hay varios lugares de interes, como la calle
        Florida, el centro comercial Galerías Pacifíco, la zona de Puerto
        Madero, la plaza de Mayo y el palacio Municipal{" "}

        Nuestros clientes dicen que esta parte de {ciudades.nombre} es su
        favorita, según los comentarios independientes.

        El Hotel es un hotel sofisticado de 4 estrellas que goza de una
        ubicación tranquila, a poca distancia de prestigiosas galerías de arte,
        teatros, museos y zonas comerciales. Además, hay WiFi gratuita. El
        establecimiento sirve un desayuno variado de 07:00 a 10:30.",1,"Grand Hotel",1,1);
INSERT INTO imagenes (id,titulo,url,producto_id) VALUES (2,"i2","https://cdn.kiwicollection.com/media/property/PR003304/xl/003304-01-lobby-chandelier.jpg?cb=1265732009",1);
INSERT INTO imagenes (id,titulo,url,producto_id) VALUES (3,"i3","https://cdn.kiwicollection.com/media/property/PR003304/xl/003304-02-restaurant-dining-area.jpg?cb=1265732009",1);
INSERT INTO imagenes (id,titulo,url,producto_id) VALUES (4,"i4","https://cdn.kiwicollection.com/media/property/PR003304/xl/003304-03-exterior-patio-couch.jpg?cb=1265732009",1);
INSERT INTO imagenes (id,titulo,url,producto_id) VALUES (1,"i1","https://cdn.kiwicollection.com/media/property/PR003304/xl/Palacio-Duhau-Park-Hyatt-Buenos-Aires-003304-01-BUEPH_141_Facade_Garden_47588.jpg?cb=1435710300",1);
INSERT INTO imagenes (id,titulo,url,producto_id) VALUES (5,"i5","https://cdn.kiwicollection.com/media/property/PR003304/xl/003304-04-bedroom-king-bed.jpg?cb=1265732009",1);
INSERT INTO caracteristicas (id,titulo,url_imagen) VALUES (1,"Wifi","https://argenlombiaimages.s3.amazonaws.com/iconos/wifi.png");
INSERT INTO caracteristicas (id,titulo,url_imagen) VALUES (2,"Cocina","https://argenlombiaimages.s3.amazonaws.com/iconos/cocina.png");
INSERT INTO caracteristicas (id,titulo,url_imagen) VALUES (3,"Automovil","https://argenlombiaimages.s3.amazonaws.com/iconos/carro.png");
INSERT INTO caracteristicas (id,titulo,url_imagen) VALUES (4,"Aire Acondicionado","https://argenlombiaimages.s3.amazonaws.com/iconos/aire.png");

INSERT INTO productos_x_caracteristicas(id_producto,id_caracteristica) VALUES (1,1);
INSERT INTO productos_x_caracteristicas(id_producto,id_caracteristica) VALUES (1,2);
INSERT INTO productos_x_caracteristicas(id_producto,id_caracteristica) VALUES (1,3);
INSERT INTO productos_x_caracteristicas(id_producto,id_caracteristica) VALUES (1,4);

INSERT INTO usuarios (id,nombre,apellido,email,nombre_usuario,password,enabled) VALUES (1,"Erick","Vaernet","erickadrielnet@gmail.com","erickadrielnet@gmail.com","$2a$10$K5f.U5jlPJUlpzMii/.fkukZCpUantou.ivKnAL0iw8DNn3i.Acv6",1);
INSERT INTO usuarios_x_roles (usuario_id,rol_id) VALUES (1,2);
INSERT INTO usuarios_x_roles (usuario_id,rol_id) VALUES (1,1);

INSERT INTO politicas (id,descripcion,titulo_id) VALUES (1,"Check-Out: 10:00. No se permiten fiestas. No fumar",1);
INSERT INTO politicas (id,descripcion,titulo_id) VALUES (2,"Se aplican las pautas de distanciamiento social y otras normas relacionadas con el coronavirus. Detector de humo. Depósito de seguridad",2);
INSERT INTO politicas (id,descripcion,titulo_id) VALUES (3,"Agregá las fechas de tu viaje para obtener los detalles de cancelacion de esta estadía",3);

INSERT INTO productos_x_politicas (id_producto,politica_id) VALUES (1,1);
INSERT INTO productos_x_politicas (id_producto,politica_id) VALUES (1,2);
INSERT INTO productos_x_politicas (id_producto,politica_id) VALUES (1,3);