CREATE TABLE author (
  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  name VARCHAR(200)  NOT NULL  ,
  birth DATE  NULL  ,
  death DATE  NULL  ,
  country VARCHAR(150)  NULL  ,
  about TEXT  NULL    ,
PRIMARY KEY(id));



CREATE TABLE book (
  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  author_id INTEGER UNSIGNED  NOT NULL  ,
  title VARCHAR(200)  NOT NULL  ,
  synopsis TEXT  NULL  ,
  genre VARCHAR(300)  NULL  ,
  original_language VARCHAR(30)  NULL  ,
  price FLOAT  NOT NULL  ,
  image_url VARCHAR(500)  NULL  ,
  available_languages VARCHAR(150) NULL    ,
  published_year INTEGER UNSIGNED  NULL    ,
PRIMARY KEY(id)  ,
INDEX book_FKIndex1(author_id),
  FOREIGN KEY(author_id)
    REFERENCES author(id)
      ON DELETE CASCADE
      ON UPDATE NO ACTION);

SELECT * FROM book;
SELECT * FROM author;

INSERT INTO `spring_on_my_own`.`author` (`name`, `birth`, `death`, `country`, `about`) VALUES ('Joaquim Maria Machado de Assis', '1839-06-21', '1908-09-29', 'Brazil', 'Joaquim Maria Machado de Assis was a pioneer Brazilian novelist, poet, playwright and short story writer, widely regarded as the greatest writer of Brazilian literature.[2][3][4] Nevertheless, Assis did not achieve widespread popularity outside Brazil during his lifetime. In 1897 he founded and became the first President of the Brazilian Academy of Letters. He was multilingual, having taught himself French, English, German and Greek in later life.');
INSERT INTO `spring_on_my_own`.`author` (`name`, `birth`, `death`, `country`, `about`) VALUES ('Aluísio Tancredo Gonçalves de Azevedo', '1857-04-14', '1913-01-21', 'Brazil', 'Aluísio Tancredo Gonçalves de Azevedo was a Brazilian novelist, caricaturist, diplomat, playwright and short story writer. Initially a Romantic writer, he would later adhere to the Naturalist movement. He introduced the Naturalist movement in Brazil with the novel O Mulato, in 1881.');
INSERT INTO `spring_on_my_own`.`author` (`name`, `birth`, `death`, `country`, `about`) VALUES ('José Martiniano de Alencar', '1829-05-01', '1877-12-12', 'Brazil', 'José Martiniano de Alencar was a Brazilian lawyer, politician, orator, novelist and dramatist. He is considered to be one of the most famous and influential Brazilian Romantic novelists of the 19th century, and a major exponent of the literary tradition known as \"Indianism\". Sometimes he signed his works with the pen name Erasmo.');
INSERT INTO `spring_on_my_own`.`author` (`name`, `birth`, `death`, `country`, `about`) VALUES ('Antônio Gonçalves Dias', '1823-08-10', '1864-11-03', 'Brazil', 'Antônio Gonçalves Dias was a Brazilian Romantic poet, playwright, ethnographer, lawyer and linguist. A major exponent of Brazilian Romanticism and of the literary tradition known as \"Indianism\", he is famous for writing \"Canção do exílio\" (arguably the most well-known poem of Brazilian literature), the short narrative poem I-Juca-Pirama, the unfinished epic Os Timbiras, and many other nationalist and patriotic poems that would award him posthumously with the title of national poet of Brazil. He was also an avid researcher of Native Brazilian languages and folklore.');

INSERT INTO `spring_on_my_own`.`book` (`author_id`, `title`, `synopsis`, `genre`, `original_language`, `price`, `available_languages`, `published_year`) VALUES ('1', 'The Posthumous Memoirs of Bras Cubas', 'The novel is narrated by the dead protagonist Brás Cubas, who tells his own life story from beyond the grave, noting his mistakes and failed romances.', 'raomance, fiction', 'Brazilian Portuguese', '39.99', 'Brazilian Portuguese, English, French, German', '1881');
INSERT INTO `spring_on_my_own`.`book` (`author_id`, `title`, `synopsis`, `genre`, `original_language`, `price`, `available_languages`, `published_year`) VALUES ('1', 'Dom Casmurro', 'Machado de Assis shows a different version of the classical adultery: the story is told through the eyes of Bento Santiago (Bentinho), the betrayed husband. Throughout the story, the character narrates the supposed betrayal of his beloved Capitu, a version of Desdemona, who, according to him, cheated on him with his best friend, giving birth to a son that only later he \"found out\" to be not his. However, the facts that he shows as proof are very flimsy, and could easily be interpreted as paranoia.', 'romance, fiction, memoir, literary realism', 'Brazilian Portuguese', '29.99', 'Brazilian Portuguese, English, French, Spanish', '1899');
INSERT INTO `spring_on_my_own`.`book` (`author_id`, `title`, `synopsis`, `genre`, `original_language`, `price`, `available_languages`, `published_year`) VALUES ('3', 'Iracema', 'The story revolves around the relationship between the Tabajara indigenous woman, Iracema, and the Portuguese colonist, Martim, who was allied with the Tabajara nation\'s enemies, the Pitiguaras.', 'romance', 'Brazilian Portuguese', '28.99', 'Brazilian Portuguese, Portuguese Portuguese, French', '1865');
