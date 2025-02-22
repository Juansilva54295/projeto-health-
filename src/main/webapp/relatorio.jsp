<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="controle.Paciente" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>RELATÓRIO</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #3cacb7 ;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 200vh;
            margin: 0;
            flex-direction: column;
        }
      
        .header-center-top {
        width: 100%;
        max-width: 845px;
        background-color: #032023;
        padding: 10px 20px;
        color:#3cacb7;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-radius: 10px 10px 0 0;
    }

    .header-center-top h1 {
        font-size: 24px;
        font-weight: bold;
        color: #d0f0ff;
    }
    
    .header-center-top img {
        width: 80px; /* Ajuste o tamanho para algo pequeno e proporcional */
        height: auto; /* Mantém a proporção */
        max-width: 100%; /* Garante que a imagem não ultrapasse o contêiner */
        border-radius: 50%;
        background-color: #032023;
        object-fit: contain; /* Garante que a imagem se ajuste ao contêiner */
    }

       

        .header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .header .info {
            display: flex;
            align-items: center;
        }

        .header .info img {
            width: 60px;
            height: 60px;
            border-radius: 60%;
            margin-right: 20px;
        }

        .header .info .text-info {
            font-size: 14px;
        }

        .header .icons button {
            background-color: transparent;
            border: none;
            cursor: pointer;
        }

        .header .icons button img {
            width: 40px;
            height: 40px;
        }

        .monitoring-board {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color:#e0f7ff ;
    padding: 200px;
    border-radius: 10px;
    width: 100%;
    max-width: 800px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin-top: 20px;
}


        .monitoring-board h2 {
            margin-bottom: 10px;
            font-size: 18px;
        }

        .monitoring-board .data-table {
            width: 100%;
            border-collapse: collapse;
        }

        /* Adiciona borda às células da tabela */
        .monitoring-board .data-table td {
            border: 3px solid  #032023;
            padding: 10px;
            text-align: left;
            vertical-align: top;
            font-size: 15px;
        }

        /* Estilo para títulos dentro da tabela */
        .monitoring-board .data-table td strong {
            font-weight: bold;
        }
.container {
    width: 80%;
    max-width: 845px; /* Limita a largura máxima */
    margin: 0 auto;
    background-color: #7ec7c9;
    border-radius: 1px 1px 15px 15px;
    padding: 20px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

/* Define o estilo da tabela para reduzir o espaçamento e alinhar os campos */
.monitoring-board {
    width: 100%;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin-top: 20px;
}

.data-table {
    width: 100%;
    table-layout: fixed; /* Define a largura fixa para as células */
    border-collapse: collapse;
}

.data-table td {
    padding: 5px; /* Reduz o padding */
    border: 3px solid #032023;
    font-size: 15px;
    vertical-align: top;
}

/* Ajusta os campos de entrada para ocupar toda a largura da célula */
input[type="text"], textarea {
    width: 100%;
    padding: 8px;
    font-size: 14px;
    box-sizing: border-box;
    margin-top: 5px;
    border-radius:7px;
     border: 2px solid #032023 ;
    
}

textarea {
    resize: vertical; /* Permite redimensionar o campo de texto verticalmente */
}

/* Centraliza os botões de ação e define espaçamento entre eles */
.action-buttons {
    display: flex;
    justify-content: space-between;
    gap: 20px;
    flex-wrap: wrap; /* Espaçamento entre os botões */
}

.action-buttons button {
    padding: 10px 20px;
    background-color: #032023;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
}

.action-buttons button:hover {
    background-color: #028482;
}
    </style>
</head>
<body>
 
    <div class="header-center-top">
        <h1>HEALTH +</h1>
        <img src="logo2.png" alt="Imagem do Logotipo"/>
    </div>

    <div class="container">
        <div class="header">
            <div class="info">
                <img src="perfil.png" alt="Foto do Paciente"/>
                <div class="text-info">
                    <strong>NOME:</strong> ${nomePaciente}<br>
                    <strong>CPF:</strong> ${cpf}<br>
                </div>
            </div>
            <div class="icons">
                <button onclick="window.location='ListaPacientesServlet';">
                    <img src="VOLTAR.png" alt="Botão Voltar"/>
                </button>
            </div>
        </div>

        <div class="monitoring-board">
            <h2>RELATÓRIO</h2>
            <form action="SalvarEdicaoRelatorioServlet" method="POST">
                <table class="data-table">
                    <tr>
                        <td><strong>IDADE DO PACIENTE:</strong> 
                            <input type="text" name="idade" value="${idade}" required>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>LEITO:</strong>
                            <input type="text" name="leito" value="${leito}" required>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>DATA DE NASCIMENTO:</strong>
                            <input type="text" name="dataNascimento" value="${dataNascimento}" required>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>DIAGNÓSTICO:</strong>
                            <textarea name="diagnostico" rows="3" required>${diagnostico}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>CID:</strong>
                            <input type="text" name="cid" value="${cid}" required>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>MEDICAMENTOS:</strong>
                            <textarea name="medicamentos" rows="3" required>${medicamentos}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>CUIDADOS:</strong>
                            <textarea name="cuidados" rows="3" required>${cuidados}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>HISTÓRICO MÉDICO:</strong>
                            <textarea name="hist_medico" rows="3" required>${hist_medico}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>PROGNÓSTICO:</strong>
                            <textarea name="prognostico" rows="3" required>${prognostico}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>CÓDIGO DO PACIENTE:</strong> 
                            <input type="text" name="codpc" value="${codpc}" required>
                        </td>
                    </tr>
                </table>
                <div class="action-buttons">
           <button type="submit" class="save-button">Salvar</button>
           </div>
              </form>
              
  
       <form action="DeletarRelatorioServlet" method="POST">
    <input type="hidden" name="codpc" value="${param.codpc}">
    <div class="action-buttons">
        <button type="submit" onclick="return confirm('Tem certeza que deseja deletar o relatório?')">Deletar Relatório</button>
    </div>
</form>

              </div>

  
        </div>
    </div>

   
</body>
</html>
