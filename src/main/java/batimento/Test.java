package batimento;

import com.fazecast.jSerialComm.SerialPort;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@WebServlet("/batimento")
public class Test extends HttpServlet {
    private SerialPort arduinoPort;
    private String ultimoBpm = "0";
    private int zeroCount = 0;
    private boolean alertaCritico = false;

    @Override
    public void init() throws ServletException {
        super.init();
        arduinoPort = SerialPort.getCommPort("COM7");
        arduinoPort.setComPortParameters(9600, 8, 1, 0);
        arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);

        if (arduinoPort.openPort()) {
            System.out.println("Porta serial aberta com sucesso!");
            new Thread(this::lerDadosArduino).start();
        } else {
            System.out.println("Erro ao abrir a porta serial.");
        }
    }

    private void lerDadosArduino() {
        Scanner scanner = new Scanner(arduinoPort.getInputStream());
        while (scanner.hasNextLine()) {
            ultimoBpm = scanner.nextLine().trim();
            if ("0".equals(ultimoBpm)) {
                zeroCount++;
                if (zeroCount > 4) {
                    alertaCritico = true;
                }
            } else {
                zeroCount = 0;
                alertaCritico = false;
            }
            System.out.println("BPM: " + ultimoBpm + " | Alert: " + alertaCritico);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        if (alertaCritico) {
            out.print("alerta");
        } else {
            out.print(ultimoBpm);
        }
        out.flush();
    }
    public void destroy() {
        if (arduinoPort != null) {
            arduinoPort.closePort(); 
        }
        super.destroy();
    }
}
