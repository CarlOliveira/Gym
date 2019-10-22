create database gym;
use gym;

create table aluno(
nome varchar(255),
cpf varchar(14) not null primary key,
telefone varchar(14),
email varchar(255),
endereco varchar(255)
);

create table instrutor(
nome varchar(255),
cpf varchar(14) not null primary key,
telefone varchar(14),
email varchar(255),
endereco varchar(255),
turno varchar(255)
);

create table exercicio(
exercicio varchar(255) not null primary key,
agrupamento_muscular varchar(255)
);

create table treino(
cpf varchar(255),
cpf_Instrutor varchar(255),
exercicio varchar(255),
descricao text,
data_treino varchar(20),
turno varchar(255),
idTreino int not null auto_increment primary key,
constraint alunoTreinoFK foreign key (cpf) references aluno(cpf),
constraint InstrutorTreinoFK foreign key (cpf_Instrutor) references Instrutor(cpf),
constraint ExercicioTreinoFK foreign key (exercicio) references Exercicio(exercicio)
);
