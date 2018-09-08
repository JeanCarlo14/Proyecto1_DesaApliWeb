--- Nombre de la base de datos BD_Project1 ----

Create Database BD_Project1;
GO

USE [BD_Project1]
GO

---------- Tabla 1 Usuarios -----------
CREATE TABLE [Usuarios](
		[Id]		          [int]           NOT NULL,
		[Email] 			    [nvarchar](255) NOT NULL,
		[Pass]				    [nvarchar](16)  NOT NULL,
		[Nombre]			    [nvarchar](255) NOT NULL,
		[Apellido1]				[nvarchar](255) NOT NULL,
    [Apellido2]				[nvarchar](255) NOT NULL

  CONSTRAINT [PK_Usuarios] PRIMARY KEY CLUSTERED 
  (    [Id] ASC
  )WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF)
)
GO

---------- Tabla 2 Tarjetas -----------
CREATE TABLE [Tarjetas](
		[Numero]		      [int]           NOT NULL,
		[Usuario] 			  [int]           NOT NULL,
		[Fecha_Exp]				[date]          NOT NULL,
		[Ccv]			        [int]           NOT NULL

  CONSTRAINT [PK_Tarjetas] PRIMARY KEY CLUSTERED 
  (    [Numero] ASC
  )WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF)
)
GO

------ FOREIGN KEY ---------
ALTER TABLE [Tarjetas]  WITH CHECK ADD  CONSTRAINT [FK1_Tarjetas] FOREIGN KEY([Usuario])
REFERENCES [Usuarios] ([Id])
GO

ALTER TABLE [Tarjetas] CHECK CONSTRAINT [FK1_Tarjetas]
GO

---------- Tabla 3 Carritos -----------
CREATE TABLE [Carritos](
		[Id]		            [int]           NOT NULL,
		[Usuario] 			    [int]           NOT NULL,
		[Checkout]				[bit]          NOT NULL,

  CONSTRAINT [PK_Carrito] PRIMARY KEY CLUSTERED 
  (    [Id] ASC
  )WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF)
)
GO

------ FOREIGN KEY ---------
ALTER TABLE [Carritos]  WITH CHECK ADD  CONSTRAINT [FK1_Carrito] FOREIGN KEY([Usuario])
REFERENCES [Usuarios] ([Id])
GO

ALTER TABLE [Carritos] CHECK CONSTRAINT [FK1_Carrito]
GO



---------- Tabla 4 Marcas -----------
CREATE TABLE [Marcas](
		[Id]		          [int]           NOT NULL,
		[Nombre] 		    	[nvarchar](255) NOT NULL

  CONSTRAINT [PK_Marcas] PRIMARY KEY CLUSTERED 
  (    [Id] ASC
  )WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF)
)
GO



---------- Tabla 5 Productos -----------
CREATE TABLE [Productos](
		[Id]		            [int]             NOT NULL,
		[Marca] 		    	  [int]             NOT NULL,
		[Descripcion]			  [nvarchar](max)   NOT NULL,
    [Precio]            [decimal](25,6)   NOT NULL,
    [Cantidad]          [int]             NOT NULL

  CONSTRAINT [PK_Productos] PRIMARY KEY CLUSTERED 
  (    [Id] ASC
  )WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF)
)
GO

------ FOREIGN KEY ---------
ALTER TABLE [Productos]  WITH CHECK ADD  CONSTRAINT [FK1_Productos] FOREIGN KEY([Marca])
REFERENCES [Marcas] ([Id])
GO

ALTER TABLE [Productos] CHECK CONSTRAINT [FK1_Productos]
GO

---------- Tabla 6 Items -----------
CREATE TABLE [Items](
		[Id]		            [int]           NOT NULL,
		[Carrito] 		    	[int]           NOT NULL,
		[Producto]			    [int]           NOT NULL,
    [Cantidad]          [int]           NOT NULL

  CONSTRAINT [PK_Items] PRIMARY KEY CLUSTERED 
  (    [Id] ASC
  )WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF)
)
GO

------ FOREIGN KEY ---------
ALTER TABLE [Items]  WITH CHECK ADD  CONSTRAINT [FK1_Item] FOREIGN KEY([Carrito])
REFERENCES [Carritos] ([Id])
GO

ALTER TABLE [Items] CHECK CONSTRAINT [FK1_Item]
GO

ALTER TABLE [Items]  WITH CHECK ADD  CONSTRAINT [FK2_Item] FOREIGN KEY([Producto])
REFERENCES [Productos] ([Id])
GO

ALTER TABLE [Items] CHECK CONSTRAINT [FK2_Item]
GO




---------- Tabla 7 Categorias -----------
CREATE TABLE [Categorias](
		[Id]		            [int]           NOT NULL,
		[Nombre] 		    	[nvarchar](255) NOT NULL

  CONSTRAINT [PK_Categoria] PRIMARY KEY CLUSTERED 
  (    [Id] ASC
  )WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF)
)
GO


---------- Tabla 8 CategoriasProductos -----------
CREATE TABLE [CategoriasProductos](
    [Categoria] 		    	[int]           NOT NULL,
		[Producto]		        [int]           NOT NULL
  CONSTRAINT [PK_CategoriasProductos] PRIMARY KEY CLUSTERED 
  (    
      [Categoria] ASC,
      [Producto] ASC       
  )WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF)
)
GO

------ FOREIGN KEY ---------
ALTER TABLE [CategoriasProductos]  WITH CHECK ADD  CONSTRAINT [FK1_CategoriasProductos] FOREIGN KEY([Categoria])
REFERENCES [Categorias] ([Id])
GO

ALTER TABLE [CategoriasProductos] CHECK CONSTRAINT [FK1_CategoriasProductos]
GO

ALTER TABLE [CategoriasProductos]  WITH CHECK ADD  CONSTRAINT [FK2_CategoriasProductos] FOREIGN KEY([Producto])
REFERENCES [Productos] ([Id])
GO

ALTER TABLE [CategoriasProductos] CHECK CONSTRAINT [FK2_CategoriasProductos]
GO


/* prodecimientos  */
create PROCEDURE PA001
AS
BEGIN
SELECT PRODUCTOS.ID,MARCAS.Nombre AS MARCA,DESCRIPCION,PRECIO,CANTIDAD FROM PRODUCTOS,MARCAS WHERE Productos.Marca = MARCAS.Id 
END
GO; 




