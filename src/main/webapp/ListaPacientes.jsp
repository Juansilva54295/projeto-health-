<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="controle.Paciente" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Pacientes - Quadro de Monitoramento</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #3cacb7;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
        flex-direction: column;
    }

    .header-center-top {
        width: 100%;
        max-width: 1200px;
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
        width: 60px;
        height: auto;
        max-width: 100%;
        border-radius: 50%;
        background-color: #032023;
        object-fit: contain;
    }

    .container {
        width: 90%;
        max-width: 1200px;
        background-color: #7ec7c9;
        border-radius: 1px 1px 15px 15px;
        padding: 20px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        position: relative;
    }

    .monitoring-board {
        display: flex;
        flex-direction: column;
        align-items: center;
        background-color:#e0f7ff;
        padding: -5px;
        border-radius: 10px;
        width: 100%;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .header-pacientes {
        display: flex;
        align-items: center;
        justify-content: space-between;
        width: 100%;
        padding: 10px;
    }

    .header-pacientes h2 {
        font-size: 18px;
        font-weight: bold;
        text-align: center;
        margin: 0;
    }

    .registrar-button {
        background-color: #032023;
        color: white;
        padding: 10px;
        font-size: 16px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
        margin-right: 20px;
    }

    .registrar-button:hover {
        background-color: #217e91;
    }

    .data-table {
        width: 98%;
        border-collapse: collapse;
        margin-top: 10px;
         border-spacing: 5px;
    }

    .data-table, .data-table tr, .data-table th, .data-table td {
        padding:15px;
        text-align: left;
        font-size: 16px;
        
    }

    .data-table th {
        background-color: #032023;
        color: white;
        
        
    }
    .data-table th:nth-child(2){
    width: 1000px;
}
    .data-table td {
        color: black;
        border-bottom: 1px solid #ddd;
    }
      .data-table td.nome {
        color: black;
        border-bottom: 1px solid #ddd;
        text-align: left; /* Garante que o cabeçalho "Nome" esteja alinhado à esquerda */
        padding-left:910px;
    }
    

       .data-table span.patient-name {
        font-weight: bold;
        color: #032023;
         position: relative;
         left: -10px;
    }

    .report-link {
        text-align: right;
        width: 100px;
    }

    .back-button {
        background-color: transparent;
        border: none;
        cursor: pointer;
        display: flex;
        align-items: center;
    }

    .back-button img {
        width: 40px;
        height: 40px;
        margin-right: 8px;
    }

    /* Linha separando o ID e o nome */
    .data-table td {
        borde: 2px solid #ddd;
         padding-left: 15px; 
        
        /* Linha de separação */
    }
     .data-table .linha-vertical {
            width: 2px;              /* Largura da linha */ /* Cor da linha */
            margin: 2px 0;          /* Espaçamento acima e abaixo */
        }
</style>
</head>
<body>

    <!-- Topo da página -->
    <div class="header-center-top">
        <h1>LISTA DE PACIENTES</h1>
        <img src="logo2.png" alt="Logotipo"/>
    </div>

    <!-- Container Principal -->
    <div class="container">
        <div class="monitoring-board">
            <div class="header-pacientes">
                <button onclick="window.location='Principal.html';" class="back-button">
                    <img src="VOLTAR.png" alt="Botão Voltar"/>
                </button>

                <!-- Título centralizado -->
                <h2>Pacientes</h2>
                
                <!-- Formulário de pesquisa -->
                <form method="get" action="ListaPacientesServlet" style="display: flex; align-items: center;">
                    <input type="text" name="searchQuery" placeholder="Pesquisar por ID ou Nome" 
                    style="padding: 8px; font-size: 14px; border-radius: 5px; border: 1px solid #ddd;">
                    <button type="submit" class="registrar-button" style="margin-left: 10px;">Pesquisar</button>
                    <!-- Botão de "Mostrar Todos" -->
                    <button type="submit" name="searchQuery" value="" class="registrar-button" style="margin-left: 10px;">Mostrar Todos</button>
                </form>

                <!-- Botão Adicionar Registro -->
                <button onclick="window.location='registrar.html';" class="registrar-button">Adicionar Registro</button>
            </div>  

            <table class="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th class = "nome">Nome</th>
                        <th>Relatórios</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    List<Paciente> pacientes = (List<Paciente>) request.getAttribute("pacientes");

                    if (pacientes != null && !pacientes.isEmpty()) {
                        for (Paciente paciente : pacientes) {
                    %>
                    <tr>
                        <!-- Exibindo ID formatado -->
                        <td><%= String.format("%05d", paciente.getId()) %></td>
                        <td class="linha-vertical"><span class="patient-name"><%= paciente.getNome() %></span></td>
                       <td class="report-link"><a href="relatorioPaciente?codpc=<%= paciente.getId() %>">Relatório</a></td>
                    </tr>
                    <% 
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="3">Nenhum paciente encontrado</td>
                    </tr>
                    <% 
                    }
                    %>
                </tbody>
            </table>
        </div>
    </div>

</body>
</html>
