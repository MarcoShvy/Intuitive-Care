# DESAFIOS DE TESTE PARA INTUITIVE CARE


## 1- TESTE WEB SCRAPING

### Estrutura
O projeto está organizado nas seguintes principais pastas:

config: Contém arquivos de configuração para o aplicativo.

controller: Gerencia o processo de scraping e interage com outros componentes.

services: Lida com a lógica central de scraping, incluindo a extração e processamento de dados.

src/main/java/com/IntuitiveCare/WebScraping: Diretório onde se encontra o arquivo principal WebScrapingApplication.java, que é o ponto de entrada do aplicativo.

### Pré-requisitos
Para rodar este projeto, você precisa de:

Java 8 ou superior.

Maven para gerenciamento de dependências.

### Como Começar
1. Clonar o repositório
git clone https://github.com/MarcoShvy/Intuitive-Care.git

2. Instalar as dependências
Para instalar o Maven e configurá-lo no PATH no seu sistema, siga as etapas abaixo, dependendo do seu sistema operacional.

1. Baixar o Maven
Primeiro, baixe a versão mais recente do Maven:

Acesse a página oficial de downloads do Maven: Maven Downloads.

Baixe o arquivo apache-maven-x.x.x-bin.zip (onde x.x.x é a versão mais recente).

Extraia o arquivo para um diretório de sua escolha (por exemplo, C:\Program Files\Apache\Maven no Windows ou /usr/local/apache-maven no Linux/macOS).

2. Configurar o Maven no PATH
No Windows:
Definir a variável de ambiente MAVEN_HOME:

Clique com o botão direito no Menu Iniciar e selecione Sistema.

Clique em Configurações avançadas do sistema.

Selecione Variáveis de Ambiente.

Em Variáveis do sistema, clique em Novo.

Nome da variável: MAVEN_HOME

Valor da variável: Caminho do diretório Maven (ex.: C:\Program Files\Apache\Maven).

Adicionar o Maven ao PATH:

Na mesma janela de Variáveis de Ambiente, encontre a variável Path na seção Variáveis do sistema e clique em Editar.

Adicione o caminho para a pasta bin dentro do diretório Maven (ex.: C:\Program Files\Apache\Maven\apache-maven-x.x.x\bin).

Verificar a instalação:

Abra o Prompt de Comando e digite:

mvn -version

entrar no diretório do projeto e rodar:

mvn install

4. Rodando o Aplicativo
Execute o aplicativo principal com o comando:

mvn spring-boot:run
### 4. Configuração
As configurações podem ser modificadas na pasta config, onde diversos parâmetros relacionados aos alvos de scraping estão definidos.

## 2 - TESTE TRANSFORMAÇÃO DE DADOS

### Projeto de Transformação de Dados
Este projeto faz parte do repositório IntuitiveCare e tem como objetivo realizar a transformação de dados, preparando-os para análise ou integração com outros sistemas. O código está organizado de maneira modular, facilitando a manutenção e expansão.

Estrutura
O projeto está organizado nas seguintes pastas principais:

data: Contém os dados de entrada e saída utilizados no processo de transformação.

src: Contém o código fonte da aplicação, incluindo classes para manipulação e transformação dos dados.

tests: Contém os testes automatizados que garantem a integridade e o bom funcionamento do sistema.

### Para rodar este projeto, você precisa de:

Python 3.x

Dependências do projeto, que podem ser instaladas com o pip.


### Como Começar
1. Clonar o repositório
git clone https://github.com/MarcoShvy/Intuitive-Care.git

### Instalando depedências

na pasta Intuitive-Care\DataTransformation rodar:
pip install -r requirements.txt


### Rodando Projeto

No diretório raiz rodar python src/main.py
