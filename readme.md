# Getting Started
## Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.1/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.1/maven-plugin/build-image.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.1/reference/using/devtools.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.1/reference/web/servlet.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.1/reference/data/sql.html#data.sql.jpa-and-spring-data)

## Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

## Maven Parent overrides
Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

## Entidades
### User (entidade base para Aluno e Professor)
id (UUID ou Long)
name
email (único)
password
role (para diferenciar Aluno e Professor)

### Student (herda de User)
enrollmentDate
grades (lista de notas)
Relacionamento com disciplinas e pagamentos.

### Teacher (herda de User)
hiringDate
Relacionamento com disciplinas.

### Discipline
id
name
description
price
Relacionamento com Professor e Alunos.

### Grade
id
value
Relacionamento com Student e Discipline.

### Billing (mensalidades)
id
dueDate
amount
status (pago ou pendente)
Relacionamento com Student.

### Payment (histórico de pagamentos)
id
date
amount
Relacionamento com Billing.

## Como adicionar "mockar" valores
Resumo da Ordem

* Criar o Student sem associações.
* Criar Discipline(s) (se necessário).
* Associar o aluno às disciplinas.
* Criar Grade(s) (notas).
* Criar Billing(s) (mensalidades).
* Criar Payment(s) (pagamentos feitos).

Seguindo essa ordem, você poderá montar corretamente todas as relações no banco de dados.

## Relações Principais
### User (Classe Base)
Student e Teacher herdam da classe base User, que contém os atributos comuns a todos os usuários, como id, name, email, password e role.

### Student e Discipline
Relacionamento @ManyToMany:
Um aluno pode estar matriculado em várias disciplinas.
Uma disciplina pode ter vários alunos matriculados.
Este relacionamento é gerenciado por uma tabela de junção chamada student_discipline.

### Teacher e Discipline
Relacionamento @OneToMany:
Um professor pode ministrar várias disciplinas.
Cada disciplina é ministrada por um único professor.

### Student e Grade
Relacionamento @OneToMany:
Um aluno pode ter várias notas associadas às disciplinas em que está matriculado.

### Discipline e Grade
Relacionamento @OneToMany:
Uma disciplina pode ter várias notas relacionadas aos alunos que estão nela.

### Student e Billing
Relacionamento @OneToMany:
Um aluno pode ter várias cobranças (mensalidades).

### Billing e Payment
Relacionamento @OneToMany:
Uma cobrança pode ter vários pagamentos associados.

## Relações por Entidade
### Student
* Relações:
	* @OneToMany com Grade: Um aluno pode ter várias notas.
	* @OneToMany com Billing: Um aluno pode ter várias cobranças.
	* @ManyToMany com Discipline: Um aluno pode estar matriculado em várias disciplinas.

### Teacher
* Relações:
	* @OneToMany com Discipline: Um professor pode ministrar várias disciplinas.

### Discipline
* Relações:
	* @ManyToOne com Teacher: Uma disciplina é ministrada por um único professor.
	* @ManyToMany com Student: Uma disciplina pode ter vários alunos matriculados.
	* @OneToMany com Grade: Uma disciplina pode ter várias notas associadas.

### Grade
* Relações:
	* @ManyToOne com Student: Uma nota pertence a um único aluno.
	* @ManyToOne com Discipline: Uma nota está associada a uma única disciplina.

### Billing
* Relações:
	* @ManyToOne com Student: Uma cobrança pertence a um único aluno.
	* @OneToMany com Payment: Uma cobrança pode ter vários pagamentos associados.

### Payment
* Relações:
	* @ManyToOne com Billing: Um pagamento está associado a uma única cobrança.


## Fluxo de Dados no Sistema
### Cadastro de Aluno
- Um novo aluno é registrado e pode ser associado a disciplinas.
- Durante a matrícula, as cobranças (mensalidades) são geradas.

### Atribuição de Professores
- Professores são cadastrados e atribuídos a disciplinas.

### Registro de Notas
- Professores registram notas para os alunos em disciplinas específicas.

### Gerenciamento de Pagamentos
- Cobranças são geradas e podem receber múltiplos pagamentos.
- O status das cobranças (pago ou pendente) é atualizado com base nos pagamentos.

## Entidades que recebem FKs:
### Discipline
Recebe uma FK chamada teacher_id para representar a relação muitos para um com a entidade Teacher.

### Grade
Recebe:
- Uma FK chamada student_id para a relação muitos para um com a entidade Student.
- Uma FK chamada discipline_id para a relação muitos para um com a entidade Discipline.

### Billing
Recebe uma FK chamada student_id para representar a relação muitos para um com a entidade Student.

### Payment
Recebe uma FK chamada billing_id para representar a relação muitos para um com a entidade Billing.

## Resumo dos Relacionamentos

### Entidades com FKs:
### Discipline:
FK: teacher_id (associa a um Teacher).

### Grade:
FKs: student_id (associa a um Student), discipline_id (associa a uma Discipline).

### Billing:
FK: student_id (associa a um Student).

### Payment:
FK: billing_id (associa a um Billing).

### Entidades sem FKs (origens das relações):
### Student e Teacher:
Não possuem FKs, pois são os lados "pai" nos relacionamentos.

### User:
Classe base abstrata; não participa diretamente das FKs.