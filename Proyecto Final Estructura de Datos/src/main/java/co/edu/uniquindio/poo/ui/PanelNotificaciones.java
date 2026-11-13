package co.edu.uniquindio.poo.ui;

import co.edu.uniquindio.poo.MonederoVirtual;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Notificacion;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class PanelNotificaciones extends JPanel {

    private static final Color AZUL =
            new Color(20, 65, 120);

    private static final Color FONDO =
            new Color(245, 247, 250);

    private static final Color BLANCO =
            Color.WHITE;


    private final MonederoVirtual sistema;

    private final Runnable alActualizarDatos;


    private JComboBox<String> cmbCliente;

    private JLabel lblTotal;

    private JLabel lblPendientes;


    private DefaultTableModel modeloPendientes;

    private DefaultTableModel modeloHistorial;

    private JTable tablaHistorial;


    private final DateTimeFormatter formatoFecha =
            DateTimeFormatter.ofPattern(
                    "dd/MM/yyyy HH:mm"
            );


    public PanelNotificaciones(
            MonederoVirtual sistema,
            Runnable alActualizarDatos) {

        this.sistema =
                sistema;

        this.alActualizarDatos =
                alActualizarDatos;


        configurar();

        construir();

        actualizarDatos();
    }


    private void configurar() {

        setLayout(
                new BorderLayout(
                        0,
                        15
                )
        );


        setBackground(
                FONDO
        );


        setBorder(
                new EmptyBorder(
                        22,
                        32,
                        28,
                        32
                )
        );
    }


    private void construir() {

        add(
                crearResumen(),
                BorderLayout.NORTH
        );


        JTabbedPane tabs =
                new JTabbedPane();


        tabs.addTab(
                "Pendientes FIFO",
                crearPendientes()
        );


        tabs.addTab(
                "Historial circular",
                crearHistorial()
        );


        add(
                tabs,
                BorderLayout.CENTER
        );
    }


    private JPanel crearResumen() {

        JPanel panel =
                crearTarjeta();


        panel.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT,
                        18,
                        8
                )
        );


        panel.add(
                new JLabel(
                        "Cliente:"
                )
        );


        cmbCliente =
                new JComboBox<>();


        cmbCliente.setPreferredSize(
                new Dimension(
                        180,
                        32
                )
        );


        cmbCliente.addActionListener(
                e -> actualizarTablas()
        );


        panel.add(
                cmbCliente
        );


        lblTotal =
                new JLabel(
                        "Total: 0"
                );


        lblPendientes =
                new JLabel(
                        "Pendientes: 0"
                );


        lblTotal.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );


        lblPendientes.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );


        panel.add(
                lblTotal
        );


        panel.add(
                lblPendientes
        );


        JButton btnCircular =
                crearBoton(
                        "Ver siguiente circular"
                );


        btnCircular.addActionListener(
                e -> verSiguienteCircular()
        );


        panel.add(
                btnCircular
        );


        return panel;
    }


    private JPanel crearPendientes() {

        JPanel panel =
                crearTarjeta();


        modeloPendientes =
                crearModelo();


        JTable tabla =
                crearTabla(
                        modeloPendientes
                );


        panel.add(
                new JScrollPane(
                        tabla
                ),
                BorderLayout.CENTER
        );


        JButton btnAtender =
                crearBoton(
                        "Atender siguiente"
                );


        btnAtender.addActionListener(
                e -> atenderSiguiente()
        );


        JPanel pie =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );


        pie.setOpaque(
                false
        );


        pie.add(
                btnAtender
        );


        panel.add(
                pie,
                BorderLayout.SOUTH
        );


        return panel;
    }


    private JPanel crearHistorial() {

        JPanel panel =
                crearTarjeta();


        modeloHistorial =
                crearModelo();


        tablaHistorial =
                crearTabla(
                        modeloHistorial
                );


        panel.add(
                new JScrollPane(
                        tablaHistorial
                ),
                BorderLayout.CENTER
        );


        JButton btnLeida =
                crearBoton(
                        "Marcar seleccionada como leída"
                );


        btnLeida.addActionListener(
                e -> marcarSeleccionada()
        );


        JPanel pie =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );


        pie.setOpaque(
                false
        );


        pie.add(
                btnLeida
        );


        panel.add(
                pie,
                BorderLayout.SOUTH
        );


        return panel;
    }


    private DefaultTableModel crearModelo() {

        return new DefaultTableModel(
                new Object[]{
                        "Código",
                        "Fecha",
                        "Tipo",
                        "Mensaje",
                        "Estado"
                },
                0
        ) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {

                return false;
            }
        };
    }


    private JTable crearTabla(
            DefaultTableModel modelo) {

        JTable tabla =
                new JTable(
                        modelo
                );


        tabla.setRowHeight(
                28
        );


        tabla.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        tabla.getTableHeader()
                .setReorderingAllowed(
                        false
                );


        tabla.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(
                        90
                );


        tabla.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(
                        130
                );


        tabla.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(
                        160
                );


        tabla.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(
                        450
                );


        return tabla;
    }