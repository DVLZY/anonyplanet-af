# anonyplanet-af 匿名星球后端源码

## 建库SQL




```SQL
CREATE TABLE `works` (
  `works` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`works`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
