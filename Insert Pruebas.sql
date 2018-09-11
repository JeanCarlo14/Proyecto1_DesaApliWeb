
USE [BD_Project1]
GO

-- Insertar Usuario ---
INSERT INTO [dbo].[Usuarios]
           ([Email]
           ,[Pass]
           ,[Nombre]
           ,[Apellido1]
           ,[Apellido2])
     VALUES
           ('ejemplo@gmail.com'
           ,'1234'
           ,'Juan'
           ,'Mora'
           ,'Avalos')
GO


--- Insertar Marca ----
INSERT INTO [dbo].[Marcas]
           ([Id]
           ,[Nombre])
     VALUES
           (1
           ,'Marca1')
GO


--- Insertar Producto ------
INSERT INTO [dbo].[Productos]
           ([Id]
           ,[Marca]
           ,[Descripcion]
           ,[Precio]
           ,[Cantidad]
           ,[Imagen])
     VALUES
           (1
           ,1
           ,'Son unos lentes' 
           ,10000
           ,30
           ,'pic.jpg')
GO


--- Insertar Tarjeta -----
INSERT INTO [dbo].[Tarjetas]
           ([Numero]
           ,[Usuario]
           ,[Fecha_Exp]
           ,[Ccv])
     VALUES
           (456
           ,'ejemplo@gmail.com'
           ,'12/11/2018'
           ,357)
GO



----- Insertar Carrito -----
INSERT INTO [dbo].[Carritos]
           ([Usuario]
           ,[Checkout])
     VALUES
           ('ejemplo@gmail.com'
           ,0)
GO


---- Insertar Item ----
INSERT INTO [dbo].[Items]
           ([Carrito]
           ,[Producto]
           ,[Cantidad])
     VALUES
           (1
           ,'1'
           ,'3')
GO


INSERT INTO [dbo].[Categorias]
           ([Nombre])
     VALUES
           ('Hogar')
GO

INSERT INTO [dbo].[CategoriasProductos]
           ([Categoria]
           ,[Producto])
     VALUES
           (1
           ,1)
GO