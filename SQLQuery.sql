USE master

CREATE DATABASE FilmViet
USE FilmViet
GO

CREATE TABLE [user](
	id INT PRIMARY KEY IDENTITY,
	username VARCHAR(20) UNIQUE NOT NULL,
	[password] VARCHAR(20) NOT NULL,
	phone char(12) NOT NULL,
	fullname nvarchar(100) NOT NULL,
	email varchar(50) NOT NULL,
	isAdmin BIT NOT NULL DEFAULT 0,
	isActive BIT NOT NULL DEFAULT 1
)

CREATE TABLE video(
	id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
	title NVARCHAR(MAX) NOT NULL,
	href VARCHAR(50) UNIQUE NOT NULL,
	poster VARCHAR(MAX) NULL,
	[views] INT NOT NULL DEFAULT 0,
	shares INT NOT NULL DEFAULT 0,
	daodien NVARCHAR(50) NOT NULL,
	dienvien NVARCHAR(MAX) NOT NULL,
	theloai NVARCHAR(MAX) NOT NULL,
	mota NVARCHAR(MAX) NOT NULL,
	[description] NVARCHAR(MAX) NOT NULL,
	isActive BIT NOT NULL DEFAULT 1
)

CREATE TABLE history(
	id INT PRIMARY KEY IDENTITY,
	userId INT FOREIGN KEY REFERENCES [dbo].[user](id),
	videoId INT FOREIGN KEY REFERENCES dbo.video(id),
	viewedDate DATETIME NOT NULL DEFAULT GETDATE(),
	isLiked BIT NOT NULL DEFAULT 0,
	likedDate DATETIME NULL
)

CREATE TABLE share (
	id INT PRIMARY KEY IDENTITY,
	userId INT FOREIGN KEY REFERENCES [dbo].[user](id),
	videoId INT FOREIGN KEY REFERENCES dbo.video(id),
	email VARCHAR(200) NOT NULL,
	shareDate DATETIME NOT NULL
)

CREATE TABLE rating (
	id INT PRIMARY KEY IDENTITY,
	userId INT FOREIGN KEY REFERENCES [dbo].[user](id),
	videoId INT FOREIGN KEY REFERENCES dbo.video(id),
	rating VARCHAR(200) NOT NULL,
	ratingDate DATETIME NOT NULL
)

CREATE TABLE comment (
	id INT PRIMARY KEY IDENTITY,
	userId INT FOREIGN KEY REFERENCES [dbo].[user](id),
	videoId INT FOREIGN KEY REFERENCES dbo.video(id),
	comment NVARCHAR(MAX) NOT NULL,
	commentDate DATETIME NOT NULL
)

SELECT v.id, v.title, v.href, SUM(CAST(h.isLiked AS INT)) AS totalLike
FROM dbo.video v 
LEFT JOIN dbo.history h ON h.videoId = v.id
WHERE v.isActive = 1 
GROUP BY v.id, v.title, v.href
ORDER BY SUM(CAST(h.isLiked AS INT)) DESC


CREATE PROCEDURE SP_SelectUsersLikedVideoByVideoHref(@videoHref varchar(50))
AS BEGIN 
	SELECT u.id, u.email, u.password, u.phone, u.fullname, u.isAdmin, u.isActive, h.likedDate, u.token
	FROM dbo.video v JOIN dbo.history h ON v.id = h.videoId
					JOIN dbo.[user] u ON u.id = h.userId
	WHERE v.href = @videoHref AND u.isActive = 1 AND v.isActive = 1 AND h.isLiked = 1
END

SELECT u.id, u.email, u.password, u.phone, u.fullname, u.isAdmin, u.isActive, h.likedDate, u.token
FROM dbo.video v JOIN dbo.history h ON v.id = h.videoId
JOIN dbo.[user] u ON u.id = h.userId
WHERE v.href = 'WWreBGNp6cg' AND u.isActive = 1 AND v.isActive = 1 AND h.isLiked = 1

CREATE PROCEDURE SP_SelectUsersLikedVideoByVideoHref(@videoHref varchar(50))
AS BEGIN 
	SELECT u.id, u.email, u.password, u.phone, u.fullname, u.isAdmin, u.isActive, h.likedDate, u.token
	FROM dbo.video v JOIN dbo.history h ON v.id = h.videoId
					JOIN dbo.[user] u ON u.id = h.userId
	WHERE v.href = @videoHref AND u.isActive = 1 AND v.isActive = 1 AND h.isLiked = 1
END


CREATE PROCEDURE SP_UserShareVideoByHref(@videoHref varchar(50))
AS BEGIN 
	SELECT v.title, v.href, u.fullname, u.email AS 'Gui', s.email AS 'Nhan', s.shareDate, u.id, u.email, u.password, u.phone, u.fullname, u.isAdmin, u.isActive, u.token FROM dbo.share s 
	JOIN dbo.[user] u ON u.id = s.userId 
	JOIN dbo.video v ON v.id = s.videoId
	WHERE v.href = @videoHref
	GROUP BY v.title, v.href, u.fullname, u.email, s.email, s.shareDate, u.id, u.email, u.password, u.phone, u.fullname, u.isAdmin, u.isActive, u.token
	ORDER BY s.shareDate DESC
END

SELECT v.title, v.href, u.fullname, u.email AS 'Gui', s.email AS 'Nhan', s.shareDate, u.id, u.email, u.password, u.phone, u.fullname, u.isAdmin, u.isActive, u.token FROM dbo.share s 
	JOIN dbo.[user] u ON u.id = s.userId 
	JOIN dbo.video v ON v.id = s.videoId
	WHERE v.href = 'BfARUE6Tx1U'
	GROUP BY v.title, v.href, u.fullname, u.email, s.email, s.shareDate, u.id, u.email, u.password, u.phone, u.fullname, u.isAdmin, u.isActive, u.token
	ORDER BY s.shareDate DESC

SELECT vnp_TxnRef, videoId, userId, vnp_BankCode, SUM(vnp_Amount), vnp_ResponseCode FROM Hoadon 
WHERE vnp_ResponseCode = 24
GROUP BY vnp_TxnRef, videoId, userId, vnp_BankCode, vnp_ResponseCode

CREATE PROCEDURE SP_PaymentVnpay(@videoHref varchar(50))
AS BEGIN
SELECT u.id, u.email, u.password, u.phone, u.isAdmin, u.isActive, u.token , vnp_TxnRef, v.title, u.fullname, vnp_BankCode, SUM(vnp_Amount), vnp_ResponseCode FROM Hoadon h
JOIN dbo.[user] u ON u.id = h.userId 
JOIN dbo.video v ON v.id = h.videoId
WHERE v.href = @videoHref
GROUP BY u.id, u.email, u.password, u.phone, u.isAdmin, u.isActive, u.token , vnp_TxnRef, v.title, u.fullname, vnp_BankCode, vnp_ResponseCode
END

EXEC SP_PaymentSuccess '0HOa-A0rt_Q'

SELECT u.id, u.email, u.password, u.phone, u.isAdmin, u.isActive, u.token , vnp_TxnRef, v.title, u.fullname, vnp_BankCode, SUM(vnp_Amount), vnp_ResponseCode FROM Hoadon h
JOIN dbo.[user] u ON u.id = h.userId 
JOIN dbo.video v ON v.id = h.videoId
WHERE v.href = 'BfARUE6Tx1U' 
GROUP BY u.id, u.email, u.password, u.phone, u.isAdmin, u.isActive, u.token , vnp_TxnRef, v.title, u.fullname, vnp_BankCode, vnp_ResponseCode

SELECT TOP 4 * FROM dbo.video 
ORDER BY addDate DESC

SELECT TOP 10 * 
FROM Hoadon WHERE vnp_ResponseCode = '24'
ORDER BY vnp_PayDate DESC;

SELECT COUNT(videoId) FROM dbo.share WHERE videoId = '1'
