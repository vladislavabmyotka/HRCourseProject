-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema HR
-- -----------------------------------------------------
-- Соискатель вакансии регистрируется в системе. Сотрудник HR фиксирует результат предварительного собеседования с Соискателем и назначает при
-- необходимости техническое собеседование, по результатам которого и принимается решение  о трудоустройстве Соискателя. Сотрудник HR размещает
-- сообщения о вакансиях и управляет ими. Администратор управляет Сотрудниками и Соискателями.
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `HR` DEFAULT CHARACTER SET utf8 ;
USE `HR` ;

-- -----------------------------------------------------
-- Table `HR`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HR`.`account` (
  `idAccount` INT(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id аккаунта',
  `login` VARCHAR(60) NOT NULL COMMENT 'логин',
  `password` VARCHAR(60) NOT NULL COMMENT 'пароль',
  `attachment` VARCHAR(10) NULL COMMENT 'приндлежность к Соискателю или Сотруднику HR',
  PRIMARY KEY (`idAccount`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HR`.`candidate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HR`.`candidate` (
  `idCandidate` INT(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id соискателя',
  `surname` VARCHAR(40) NOT NULL COMMENT 'фамилия',
  `name` VARCHAR(40) NOT NULL COMMENT 'имя',
  `lastname` VARCHAR(40) NULL COMMENT 'отчество',
  `age` INT(3) NOT NULL COMMENT 'возраст',
  `email` VARCHAR(100) NOT NULL COMMENT 'e-mail',
  `address` VARCHAR(60) NOT NULL COMMENT 'адрес проживания',
  `citizenship` VARCHAR(80) NOT NULL COMMENT 'гражданство',
  `phone` VARCHAR(30) NOT NULL COMMENT 'мобильный телефон',
  `post` VARCHAR(200) NULL COMMENT 'должность',
  `education` VARCHAR(200) NULL COMMENT 'образование',
  `experience` INT(2) DEFAULT 0 NULL COMMENT 'опыт работы',
  `english` VARCHAR(50) NULL COMMENT 'уровень английского языка',
  `skill` VARCHAR(255) NULL COMMENT 'основные (ключевые) навыки Соискателя',
  `c_idAccount` INT(5) UNSIGNED NOT NULL COMMENT 'id аккаунта',
  INDEX `idAccount_idx` (`c_idAccount` ASC),
  PRIMARY KEY (`idCandidate`),
  CONSTRAINT `c_idAccount`
    FOREIGN KEY (`c_idAccount`)
    REFERENCES `HR`.`account` (`idAccount`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HR`.`employer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HR`.`employer` (
  `idEmployer` INT(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id Сотрудника HR',
  `surname` VARCHAR(30) NOT NULL COMMENT 'фамилия Сотрудника HR',
  `name` VARCHAR(30) NOT NULL COMMENT 'имя Сотрудника HR',
  `lastname` VARCHAR(30) NULL COMMENT 'отчество',
  `address` VARCHAR(60) NOT NULL COMMENT 'адрес проживания',
  `phone` VARCHAR(30) NOT NULL COMMENT 'мобильный телефон',
  `email` VARCHAR(100) NOT NULL COMMENT 'E-mail',
  `company` VARCHAR(100) NULL,
  `e_idAccount` INT(5) UNSIGNED NOT NULL COMMENT 'id аккаунта',
  PRIMARY KEY (`idEmployer`),
  INDEX `idAccount_idx` (`e_idAccount` ASC),
  CONSTRAINT `e_idAccount`
    FOREIGN KEY (`e_idAccount`)
    REFERENCES `HR`.`account` (`idAccount`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HR`.`vacancy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HR`.`vacancy` (
  `idvacancy` INT(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id вакансии',
  `post` VARCHAR(150) NOT NULL COMMENT 'должность на вакансии',
  `company` VARCHAR(60) NOT NULL COMMENT 'названия компании',
  `salary` DECIMAL(8, 2) NULL COMMENT 'заработная плата',
  `location` VARCHAR(45) NULL COMMENT 'местоположение вакансии (например Минск)',
  `experience` INT(5) DEFAULT 0 NULL COMMENT 'необходимый опыт работы',
  `english` VARCHAR(50) NULL COMMENT 'необходимый уровень английского языка',
  `text` VARCHAR(2000) NOT NULL COMMENT 'сам текст вакансии, необходимые навыки, условия работы и т.д.',
  `conditionVacancy` VARCHAR(20) NULL COMMENT 'состояние вакансии',
  `v_idEmployer` INT(5) UNSIGNED NULL COMMENT 'id Сотрудника HR',
  PRIMARY KEY (`idvacancy`),
  INDEX `idEmployer_idx` (`v_idEmployer` ASC),
  CONSTRAINT `v_idEmployer`
    FOREIGN KEY (`v_idEmployer`)
    REFERENCES `HR`.`employer` (`idEmployer`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HR`.`interview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HR`.`interview` (
  `idInterview` INT(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id собеседования',
  `i_idCandidate` INT(5) UNSIGNED NULL COMMENT 'id Соискателя',
  `i_idvacancy` INT(5) UNSIGNED NULL COMMENT 'id Вакансии',
  `preResult` VARCHAR(50) NULL COMMENT 'результат предварительного собеседования',
  `finalResult` VARCHAR(50) NULL COMMENT 'результат технического (итогового) собеседования',
  PRIMARY KEY (`idInterview`),
  INDEX `idvacancy_idx` (`i_idvacancy` ASC),
  CONSTRAINT `i_idCandidate`
    FOREIGN KEY (`i_idCandidate`)
    REFERENCES `HR`.`candidate` (`idCandidate`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `i_idvacancy`
    FOREIGN KEY (`i_idvacancy`)
    REFERENCES `HR`.`vacancy` (`idvacancy`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- заполнение данными таблицы account
-- добавление аккаунтов для пяти кандидатов и четырех Сотрудников HR
INSERT INTO account (login, password, attachment) VALUES ('wolfKing1978', 'wolfCandidate1978', 'c');
INSERT INTO account (login, password, attachment) VALUES ('BigBoomTheory', 'BigBoomTheory1', 'c');
INSERT INTO account (login, password, attachment) VALUES ('KingOfTheDot', 'qwerty1234', 'c');
INSERT INTO account (login, password, attachment) VALUES ('IvanSlayer', 'IvanAverin2000', 'c');
INSERT INTO account (login, password, attachment) VALUES ('Western Cinema', 'NormKino1', 'c');

INSERT INTO account (login, password, attachment) VALUES ('Max Direct', 'TheBestHRInThisCompany1', 'e');
INSERT INTO account (login, password, attachment) VALUES ('Eugen Frolov', 'FrolovEugen1', 'e');
INSERT INTO account (login, password, attachment) VALUES ('Krasnov Karl', 'A1234567890', 'e');
INSERT INTO account (login, password, attachment) VALUES ('Avankey Sidney', 'MetroIsLove101010', 'e');
-- -----------------------------------------------------------------

-- заполнение данными таблицы candidate
-- добавление нескольких кандидатов
INSERT INTO candidate (surname, name, lastname, age, email, address, citizenship, phone, post, education, experience, english, skill, c_idAccount) 
	VALUES ('Ivanov', 'Ivan', 'Ivanovich', 30, 'ivan.ivanov@gmail.com', 'ул. Притыцкого 12/1, 33', 'Республика Беларусь', '8 (029) 123-12-12', 
			'PHP Developer', 'БГУ Механико-математический факультет', 4, 'A0 (Абсолютный новичек)', 'Коммуникабелен, легко обучаем', 1);
INSERT INTO candidate (surname, name, lastname, age, email, address, citizenship, phone, post, education, experience, english, skill, c_idAccount) 
	VALUES ('Disney', 'Ksenia', 'Viktorovna', 20, 'SmileWether@mail.ru', 'просп. Рокоссовского 100, 1', 'Республика Беларусь',
			'+375 29 852-78-98', 'Бизнес-аналитик', 'БГУ Факультет прикладной математики и информатики', 0, 'A1+ (Базовый средний)', 'Курсовые работы по Java, C#, JavaScript. Знание Ajax, Perl.', 
            2);
INSERT INTO candidate (surname, name, lastname, age, email, address, citizenship, phone, post, education, experience, english, skill, c_idAccount) 
	VALUES ('Брусов', 'Кирилл', 'Кириллович', 43, 'bigboss42@gmail.com', 'Ул. Каменогорская 42, 33', 'Российская Федерация', '8 (029) 133-14-34',
			'DB Developer', 'НИИ Московая академия сплавов', 22, 'A2+ (Ниже среднего)', 'Огромный опыт в научной сфере, многочисленные работы по сопротивлению материалов, 11 лет преподования в МГУ', 
            3);
INSERT INTO candidate (surname, name, lastname, age, email, address, citizenship, phone, post, education, experience, english, skill, c_idAccount) 
	VALUES ('Аверин', 'Иван', 'Янович', 17, 'ivan.averin.yanovich@gmail.com', 'ул. Платонова 10, 11', 'Республика Беларусь', '8 (029) 147-57-75',
			'Junior .NET Developer', 'БГУИР КСИС', 0, 'Не указано', 'Аналитический склад ума', 4);
INSERT INTO candidate (surname, name, age, email, address, citizenship, phone, post, experience, english, skill, c_idAccount) VALUES ('Сидоров',
			'Марк', 23, 'MarkCreator@gmail.com', 'г. Витебск, ул. Маяковского 12/1б, 1', 'РБ', '+375 44 121-02-77', 'PHP developer', 1, 
            'C1 (Продвинутый)', 'Хакер по призванию', 5);                              
-- -----------------------------------------------------------------

-- заполнение данными таблицы employer
-- добавление нескольких Сотрудников HR
INSERT INTO employer (surname, name, lastname, address, phone, email, company, e_idAccount) VALUES ('Директ', 'Максим', 'Юрьевич', 
			'ул. Руссиянова 12, 1', '8 (029) 456-74-23', 'MaxDirect@gmail.com', 'BelHard', 6);
INSERT INTO employer (surname, name, lastname, address, phone, email, company, e_idAccount) VALUES ('Фролов', 'Евгений', 'Михайлович', 
			'ул. Асаналиева 1, 241', '+375 44 111-14-41', 'EugenFrolov@mail.ru', 'ITransition', 7);
INSERT INTO employer (surname, name, lastname, address, phone, email, company, e_idAccount) VALUES ('Краснов', 'Карл', 'Махмудович', 
			'ул. Кальварийская 33/1а, 124', '+375 29 320-02-45', 'karlkarlyan42@gmail.com', 'Клевер Парк', 8);
INSERT INTO employer (surname, name, address, phone, email, company, e_idAccount) VALUES ('Avankey', 'Sidney', 'Platonava st. 23, 23',
			'+375 44 147-55-84', 'SidneyAustralia101010@gmail.com', 'BelHard', 9);
-- -----------------------------------------------------------------

-- заполнение данными таблицы vacancy
-- добавление нескольких вакансий
INSERT INTO vacancy (post, company, salary, location, experience, english, text, conditionVacancy, v_idEmployer) VALUES ('PHP Developer', 
			'BelHard', 1500, 'г. Могилев', 4, 'C1+ (Выше продвинутого)', 'Понимание принципов ООП и знание паттернов проектирования; Опыт работы и уверенные знания Mysql, SQLite; Опыт работы с современными фреймворками; Хорошее знание Javascript и опыт работы с Jquery, Bootstrap; Базовое знание Unix/Linux; Базовое знание Unix/Linux;',
            'Открыта', 1);
INSERT INTO vacancy (post, company, salary, location, english, text, conditionVacancy, v_idEmployer) VALUES ('Junior .NET Developer', 'BelHard', 
			500, 'г. Минск', 'B2+ (Ниже продвинутого)', 'Теоретическая база по .NET, C#; Знание основ HTML, CSS, JavaScript; Опыт работы c .NET, C# в рамках учебных проектов (лабораторные, курсовые, диплом) приветствуется; Опыт коммерческой разработки приветствуется;',
            'Открыта', 1);
INSERT INTO vacancy (post, company, salary, location, experience, english, text, conditionVacancy, v_idEmployer) VALUES ('Бизнес-аналитик', 
			'ITransition', 450, 'г. Витебск', 1, 'B1+ (Выше среднего)', 'Работа с веб-проектами, составление технического задания,  постановка задач инженерам программистам, представление результатов работ заказчику, сдача-приемка работ',
            'Открыта', 2);
INSERT INTO vacancy (post, company, salary, location, english, text, conditionVacancy, v_idEmployer) VALUES ('Бизнес-аналитик', 'Клевер Парк', 
			380, 'г. Минск', 'A2+ (Ниже среднего)', 'Умение работать в команде, Знание достаточно большого количества ПО, Знание методологии разработки программных продуктов, Активность и желание развиваться',
            'Закрыта', 3);
INSERT INTO vacancy (post, company, salary, location, experience, english, text, conditionVacancy, v_idEmployer) VALUES ('Database developer', 
			'BelHard', 720, 'г. Гомель', 2, 'A1+ (Базовый средний)', 'Знание языка запросов SQL (например, Transact-SQL); Знание прикладного языка программирования (например, С#); Знание технологий, обеспечивающих взаимодействие, связь приложения с базой данных (ADO.NET, AbstractDomain Framework, LINQ)',
            'Открыта', 4);
-- -----------------------------------------------------------------

-- заполнение данными таблицы interview
-- добавление интервью между кандидатами на вакансию и сотрудниками HR
INSERT INTO interview (i_idCandidate, i_idvacancy, preResult, finalResult) VALUES (1, 1, 'Не указано', 'Не указано');
INSERT INTO interview (i_idCandidate, i_idvacancy, preResult, finalResult) VALUES (5, 1, 'Провал', 'Провал');
INSERT INTO interview (i_idCandidate, i_idvacancy, preResult, finalResult) VALUES (3, 5, 'Успешно', 'Успешно');
INSERT INTO interview (i_idCandidate, i_idvacancy, preResult, finalResult) VALUES (2, 3, 'Есть вопросы', 'Не указано');
INSERT INTO interview (i_idCandidate, i_idvacancy, preResult, finalResult) VALUES (2, 4, 'Успешно', 'Сомнительно');
INSERT INTO interview (i_idCandidate, i_idvacancy, preResult, finalResult) VALUES (4, 2, 'Успешно', 'Есть вопросы');
-- -----------------------------------------------------------------



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
