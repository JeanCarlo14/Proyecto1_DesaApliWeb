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
		[Id]		            int IDENTITY(1,1)  NOT NULL,
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
		[Id]		            int IDENTITY(1,1)  NOT NULL,
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
		[Id]		           int IDENTITY(1,1)  NOT NULL,
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


/* Prodecimientos  */
use BD_Project1
go

--- Seleccionar Productos ---
create PROCEDURE PA001
AS
BEGIN
SELECT PRODUCTOS.ID,MARCAS.Nombre AS MARCA,DESCRIPCION,PRECIO,CANTIDAD,IMAGEN FROM PRODUCTOS,MARCAS WHERE Productos.Marca = MARCAS.Id 
END
GO

--- Seleccionar Usuario ---
CREATE PROCEDURE PA002
  @Id int
  AS
  BEGIN
    SELECT Id, Nombre, Apellido1, Apellido2, Email FROM Usuarios WHERE Id= @Id
  END
GO

-- Crear Usuario --
CREATE PROCEDURE PA003
    @Id int, 
    @Nombre NVARCHAR(255),
    @Apellido1 NVARCHAR(255),
    @Apellido2 NVARCHAR(255),
    @Email NVARCHAR(255),
    @Pass NVARCHAR(255)
AS
BEGIN
      SET NOCOUNT ON;
            INSERT INTO Usuarios(Id, Nombre, Apellido1, Apellido2, Email, Pass)
            VALUES (@Id, @Nombre, @Apellido1, @Apellido2, @Email, @Pass);
END
GO

-- Actualizar Usuario --
CREATE PROCEDURE PA004
    @Id int, 
    @Nombre NVARCHAR(255),
    @Apellido1 NVARCHAR(255),
    @Apellido2 NVARCHAR(255),
    @Email NVARCHAR(255)
AS
BEGIN
      SET NOCOUNT ON;
        UPDATE Usuarios
            SET   Nombre = @Nombre,
            Apellido1 = @Apellido1,  Apellido2 = @Apellido2, Email = @Email
            WHERE Id = @Id 
END
GO

-- Insertar Tarjeta ---
CREATE PROCEDURE PA005
    @Numero int, 
    @Usuario int,
    @Fecha_Exp date,
    @Ccv int
AS
BEGIN
      SET NOCOUNT ON;
            INSERT INTO Tarjetas(Numero, Usuario, Fecha_Exp, Ccv)
            VALUES (@Numero, @Usuario, @Fecha_Exp, @Ccv);
END
GO

-- Seleccionar Tarjeta Por Id Tarjeta ---
CREATE PROCEDURE PA006
    @Numero int
AS
BEGIN
      SET NOCOUNT ON;
           Select Numero, Usuarios.Nombre, Fecha_Exp, Ccv from Tarjetas, Usuarios
           where Numero = @Numero and Tarjetas.Usuario = Usuarios.Id 
END
GO

-- Seleccionar Tarjeta Por Usuario Tarjeta ---
CREATE PROCEDURE PA007
    @Usuario int
AS
BEGIN
      SET NOCOUNT ON;
           Select Numero, Usuarios.Nombre, Fecha_Exp, Ccv from Tarjetas, Usuarios
           where Usuario = @Usuario and Tarjetas.Usuario = Usuarios.Id 
END
GO


----- Crear Carrito --------
CREATE PROCEDURE PA008
    @Usuario int,
    @Checkout bit
AS
BEGIN
      SET NOCOUNT ON;
            INSERT INTO Carritos(Usuario, Checkout)
            VALUES (@Usuario, @Checkout);
END
GO

---- Actualizar Carrito ------
CREATE PROCEDURE PA009
    @Id int, 
    @Checkout bit
AS
BEGIN
      SET NOCOUNT ON;
        UPDATE Carritos
            SET   Checkout = @Checkout
            WHERE Id = @Id 
END
GO


-- Seleccionar Carrito por Usuario y Checkout = 0----
CREATE PROCEDURE PA010
    @Usuario int
AS
BEGIN
      SET NOCOUNT ON;
           Select Id, Usuario, Checkout from Carritos
           where Usuario = @Usuario and Checkout = 0 
END
GO


---- Actualizar la Cantidad de Productos  ------
CREATE PROCEDURE PA011
    @Id int,
    @Can int
AS
BEGIN
      SET NOCOUNT ON;
      declare 
        @canAux int
        SELECT @canAux = Cantidad FROM Productos WHERE Id = @Id

        SELECT @canAux = @canAux - @Can

        UPDATE Productos
            SET   Cantidad = @canAux
            WHERE Id = @Id 
END
GO


----- Crear Items --------
CREATE PROCEDURE PA012
    @Carrito int,
    @Producto int,
    @Cantidad int
AS
BEGIN
      SET NOCOUNT ON;
            INSERT INTO Items(Carrito, Producto, Cantidad)
            VALUES (@Carrito, @Producto, @Cantidad);
END
GO

--- Seleccionar Items por Id Carrito ------
CREATE PROCEDURE PA013
    @Carrito int
AS
BEGIN
       SET NOCOUNT ON;
           Select Id, Carrito, Producto, Cantidad from Items
           where Carrito = @Carrito
END
GO

----- Actualizar la cantidad de un Item --------
CREATE PROCEDURE PA014
    @Id int,
    @Cantidad int
AS
BEGIN
      SET NOCOUNT ON;
      UPDATE Items
            SET   Cantidad = @Cantidad
            WHERE Id = @Id
END
GO

----- Eliminar un Item --------
CREATE PROCEDURE PA015
    @Id int
AS
BEGIN
      SET NOCOUNT ON;
        DELETE FROM Items
          WHERE Id = @Id
END
GO



--- Seleccionar Categorias ------
CREATE PROCEDURE PA016
AS
BEGIN
       SET NOCOUNT ON;
           Select Id, Nombre from Categorias
END
GO




--- Seleccionar Productos segun la categoria ------
CREATE PROCEDURE PA017
 @Categoria int
AS
BEGIN
       SET NOCOUNT ON;
           Select Id, Nombre from Categorias

SELECT Categorias.Nombre as Categoria, Productos.Id as IdProducto, Marcas.Nombre AS Marca, Descripcion, Precio , Cantidad 
  FROM CategoriasProductos, Productos, Marcas, categorias
  WHERE CategoriasProductos.Categoria = @Categoria and Productos.Id = CategoriasProductos.Producto
   and Categorias.Id = CategoriasProductos.Categoria and  Productos.Marca = MARCAS.Id 

END
GO

/* alter*/

ALTER TABLE productos ADD Imagen varchar(100); 


