package co.edu.uniquindio.poo.ui;

import co.edu.uniquindio.poo.MonederoVirtual;
import co.edu.uniquindio.poo.enums.CategoriaGasto;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Monedero;
import co.edu.uniquindio.poo.model.Transaccion;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class PanelTransacciones extends JPanel {

    private static final Color AZUL_PRINCIPAL =
            new Color(20, 65, 120);

    private static final Color AZUL_CLARO =
            new Color(230, 240, 250);

    private static final Color FONDO =
            new Color(245, 247, 250);

    private static final Color BLANCO =
            Color.WHITE;

    private static final Color TEXTO_PRINCIPAL =
            new Color(35, 43, 55);

    private static final Color TEXTO_SECUNDARIO =
            new Color(105, 115, 130);


    private final MonederoVirtual sistema;

    private final Runnable alActualizarDatos;


    // DEPÓSITO
    private JComboBox<String> cmbClienteDeposito;
    private JComboBox<String> cmbMonederoDeposito;
    private JTextField txtMontoDeposito;


    // RETIRO
    private JComboBox<String> cmbClienteRetiro;
    private JComboBox<String> cmbMonederoRetiro;
    private JTextField txtMontoRetiro;


    // TRANSFERENCIA
    private JComboBox<String> cmbClienteOrigen;
    private JComboBox<String> cmbMonederoOrigen;
    private JComboBox<String> cmbClienteDestino;
    private JComboBox<String> cmbMonederoDestino;
    private JTextField txtMontoTransferencia;


    // GASTO
    private JComboBox<String> cmbClienteGasto;
    private JComboBox<String> cmbMonederoGasto;
    private JComboBox<CategoriaGasto> cmbCategoriaGasto;
    private JTextField txtMontoGasto;


    // CONSULTAS
    private JComboBox<String> cmbClienteConsulta;
    private JComboBox<String> cmbMonederoConsulta;
    private JLabel lblSaldoConsulta;

    private JComboBox<String> cmbClienteHistorial;
    private DefaultTableModel modeloHistorial;


    private final DateTimeFormatter formatoFecha =
            DateTimeFormatter.ofPattern(
                    "dd/MM/yyyy HH:mm"
            );


    public PanelTransacciones(
            MonederoVirtual sistema,
            Runnable alActualizarDatos) {

        this.sistema = sistema;
        this.alActualizarDatos = alActualizarDatos;

        configurarPanel();
        construirInterfaz();
        actualizarDatos();
    }


    // ---------------------------------------------------------
    // CONFIGURACIÓN GENERAL
    // ---------------------------------------------------------

    private void configurarPanel() {

        setLayout(
                new BorderLayout()
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


    private void construirInterfaz() {

        JTabbedPane tabs =
                new JTabbedPane();

        tabs.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );


        tabs.addTab(
                "Depósito",
                crearPanelDeposito()
        );

        tabs.addTab(
                "Retiro",
                crearPanelRetiro()
        );

        tabs.addTab(
                "Transferencia",
                crearPanelTransferencia()
        );

        tabs.addTab(
                "Gasto",
                crearPanelGasto()
        );

        tabs.addTab(
                "Consultas",
                crearPanelConsultas()
        );


        add(
                tabs,
                BorderLayout.CENTER
        );
    }


    // ---------------------------------------------------------
    // DEPÓSITO
    // ---------------------------------------------------------

    private JPanel crearPanelDeposito() {

        JPanel campos =
                crearPanelCampos();


        cmbClienteDeposito =
                crearComboTexto();

        cmbMonederoDeposito =
                crearComboTexto();

        txtMontoDeposito =
                crearCampoTexto();


        cmbClienteDeposito.addActionListener(
                e -> actualizarMonederos(
                        cmbClienteDeposito,
                        cmbMonederoDeposito
                )
        );


        agregarCampo(
                campos,
                0,
                "Cliente",
                cmbClienteDeposito
        );

        agregarCampo(
                campos,
                1,
                "Monedero",
                cmbMonederoDeposito
        );

        agregarCampo(
                campos,
                2,
                "Monto",
                txtMontoDeposito
        );


        JButton boton =
                crearBotonPrincipal(
                        "Realizar depósito"
                );


        boton.addActionListener(
                e -> realizarDeposito()
        );


        agregarBoton(
                campos,
                3,
                boton
        );


        return crearFormulario(
                "Realizar depósito",
                "Agrega dinero al monedero y genera puntos según el monto.",
                campos
        );
    }


    // ---------------------------------------------------------
    // RETIRO
    // ---------------------------------------------------------

    private JPanel crearPanelRetiro() {

        JPanel campos =
                crearPanelCampos();


        cmbClienteRetiro =
                crearComboTexto();

        cmbMonederoRetiro =
                crearComboTexto();

        txtMontoRetiro =
                crearCampoTexto();


        cmbClienteRetiro.addActionListener(
                e -> actualizarMonederos(
                        cmbClienteRetiro,
                        cmbMonederoRetiro
                )
        );


        agregarCampo(
                campos,
                0,
                "Cliente",
                cmbClienteRetiro
        );

        agregarCampo(
                campos,
                1,
                "Monedero",
                cmbMonederoRetiro
        );

        agregarCampo(
                campos,
                2,
                "Monto",
                txtMontoRetiro
        );


        JLabel aviso =
                new JLabel(
                        "El retiro puede incluir comisión del 1 %."
                );


        aviso.setForeground(
                TEXTO_SECUNDARIO
        );

        aviso.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );


        GridBagConstraints gbc =
                restricciones();

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;


        campos.add(
                aviso,
                gbc
        );


        JButton boton =
                crearBotonPrincipal(
                        "Realizar retiro"
                );


        boton.addActionListener(
                e -> realizarRetiro()
        );


        agregarBoton(
                campos,
                4,
                boton
        );


        return crearFormulario(
                "Realizar retiro",
                "Retira dinero del monedero siempre que exista saldo suficiente.",
                campos
        );
    }


    // ---------------------------------------------------------
    // TRANSFERENCIA
    // ---------------------------------------------------------

    private JPanel crearPanelTransferencia() {

        JPanel campos =
                crearPanelCampos();


        cmbClienteOrigen =
                crearComboTexto();

        cmbMonederoOrigen =
                crearComboTexto();

        cmbClienteDestino =
                crearComboTexto();

        cmbMonederoDestino =
                crearComboTexto();

        txtMontoTransferencia =
                crearCampoTexto();


        cmbClienteOrigen.addActionListener(
                e -> actualizarMonederos(
                        cmbClienteOrigen,
                        cmbMonederoOrigen
                )
        );


        cmbClienteDestino.addActionListener(
                e -> actualizarMonederos(
                        cmbClienteDestino,
                        cmbMonederoDestino
                )
        );


        agregarCampo(
                campos,
                0,
                "Cliente origen",
                cmbClienteOrigen
        );

        agregarCampo(
                campos,
                1,
                "Monedero origen",
                cmbMonederoOrigen
        );

        agregarCampo(
                campos,
                2,
                "Cliente destino",
                cmbClienteDestino
        );

        agregarCampo(
                campos,
                3,
                "Monedero destino",
                cmbMonederoDestino
        );

        agregarCampo(
                campos,
                4,
                "Monto",
                txtMontoTransferencia
        );


        JLabel aviso =
                new JLabel(
                        "La comisión se calcula automáticamente según los beneficios activos."
                );


        aviso.setForeground(
                TEXTO_SECUNDARIO
        );

        aviso.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );


        GridBagConstraints gbc =
                restricciones();

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;


        campos.add(
                aviso,
                gbc
        );


        JButton boton =
                crearBotonPrincipal(
                        "Transferir"
                );


        boton.addActionListener(
                e -> realizarTransferencia()
        );


        agregarBoton(
                campos,
                6,
                boton
        );


        return crearFormulario(
                "Realizar transferencia",
                "Transfiere dinero entre clientes o entre monederos diferentes.",
                campos
        );
    }


    // ---------------------------------------------------------
    // GASTO
    // ---------------------------------------------------------

    private JPanel crearPanelGasto() {

        JPanel campos =
                crearPanelCampos();


        cmbClienteGasto =
                crearComboTexto();

        cmbMonederoGasto =
                crearComboTexto();

        cmbCategoriaGasto =
                new JComboBox<>(
                        CategoriaGasto.values()
                );

        configurarCombo(
                cmbCategoriaGasto
        );

        txtMontoGasto =
                crearCampoTexto();


        cmbClienteGasto.addActionListener(
                e -> actualizarMonederos(
                        cmbClienteGasto,
                        cmbMonederoGasto
                )
        );


        agregarCampo(
                campos,
                0,
                "Cliente",
                cmbClienteGasto
        );

        agregarCampo(
                campos,
                1,
                "Monedero",
                cmbMonederoGasto
        );

        agregarCampo(
                campos,
                2,
                "Categoría",
                cmbCategoriaGasto
        );

        agregarCampo(
                campos,
                3,
                "Monto",
                txtMontoGasto
        );


        JButton boton =
                crearBotonPrincipal(
                        "Registrar gasto"
                );


        boton.addActionListener(
                e -> realizarGasto()
        );


        agregarBoton(
                campos,
                4,
                boton
        );


        return crearFormulario(
                "Registrar gasto",
                "Registra un gasto y actualiza automáticamente los patrones por categoría.",
                campos
        );
    }


    // ---------------------------------------------------------
    // CONSULTAS
    // ---------------------------------------------------------

    private JPanel crearPanelConsultas() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                18
                        )
                );


        panel.setBackground(
                FONDO
        );

        panel.setBorder(
                new EmptyBorder(
                        18,
                        18,
                        18,
                        18
                )
        );


        panel.add(
                crearConsultaSaldo(),
                BorderLayout.NORTH
        );

        panel.add(
                crearHistorial(),
                BorderLayout.CENTER
        );


        return panel;
    }


    // ---------------------------------------------------------
    // CONSULTA DE SALDO
    // ---------------------------------------------------------

    private JPanel crearConsultaSaldo() {

        JPanel tarjeta =
                crearTarjeta();


        JPanel contenido =
                new JPanel(
                        new GridBagLayout()
                );


        contenido.setOpaque(
                false
        );


        contenido.setBorder(
                new EmptyBorder(
                        5,
                        0,
                        5,
                        0
                )
        );


        cmbClienteConsulta =
                crearComboTexto();

        cmbMonederoConsulta =
                crearComboTexto();


        // Un poco más pequeños para que la consulta
        // se vea correctamente incluso en ventanas reducidas.
        cmbClienteConsulta.setPreferredSize(
                new Dimension(
                        220,
                        34
                )
        );

        cmbMonederoConsulta.setPreferredSize(
                new Dimension(
                        220,
                        34
                )
        );


        cmbClienteConsulta.addActionListener(
                e -> {

                    actualizarMonederos(
                            cmbClienteConsulta,
                            cmbMonederoConsulta
                    );

                    actualizarSaldoConsulta();
                }
        );


        cmbMonederoConsulta.addActionListener(
                e -> actualizarSaldoConsulta()
        );


        lblSaldoConsulta =
                new JLabel(
                        "$ 0.00"
                );


        lblSaldoConsulta.setForeground(
                AZUL_PRINCIPAL
        );

        lblSaldoConsulta.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        23
                )
        );

        lblSaldoConsulta.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        lblSaldoConsulta.setPreferredSize(
                new Dimension(
                        230,
                        38
                )
        );


        JButton btnConsultar =
                crearBotonPrincipal(
                        "Consultar saldo"
                );


        btnConsultar.setPreferredSize(
                new Dimension(
                        180,
                        36
                )
        );


        btnConsultar.addActionListener(
                e -> actualizarSaldoConsulta()
        );


        GridBagConstraints gbc =
                new GridBagConstraints();


        gbc.insets =
                new Insets(
                        6,
                        8,
                        6,
                        8
                );

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;


        // FILA 1 - CLIENTE

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        contenido.add(
                crearEtiqueta(
                        "Cliente"
                ),
                gbc
        );


        gbc.gridx = 1;
        gbc.weightx = 1;

        contenido.add(
                cmbClienteConsulta,
                gbc
        );


        // FILA 1 - MONEDERO

        gbc.gridx = 2;
        gbc.weightx = 0;

        contenido.add(
                crearEtiqueta(
                        "Monedero"
                ),
                gbc
        );


        gbc.gridx = 3;
        gbc.weightx = 1;

        contenido.add(
                cmbMonederoConsulta,
                gbc
        );


        // FILA 2 - BOTÓN

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.fill =
                GridBagConstraints.NONE;

        contenido.add(
                btnConsultar,
                gbc
        );


        // FILA 2 - SALDO

        JLabel etiquetaSaldo =
                new JLabel(
                        "Saldo disponible:"
                );


        etiquetaSaldo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        etiquetaSaldo.setForeground(
                TEXTO_SECUNDARIO
        );


        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        contenido.add(
                etiquetaSaldo,
                gbc
        );


        gbc.gridx = 3;
        gbc.weightx = 1;

        contenido.add(
                lblSaldoConsulta,
                gbc
        );


        tarjeta.add(
                crearTitulo(
                        "Consulta de saldo"
                ),
                BorderLayout.NORTH
        );


        tarjeta.add(
                contenido,
                BorderLayout.CENTER
        );


        return tarjeta;
    }


    // ---------------------------------------------------------
    // HISTORIAL
    // ---------------------------------------------------------

    private JPanel crearHistorial() {

        JPanel tarjeta =
                crearTarjeta();


        JPanel superior =
                new JPanel(
                        new BorderLayout()
                );


        superior.setOpaque(
                false
        );


        JLabel titulo =
                crearTitulo(
                        "Historial de transacciones"
                );


        superior.add(
                titulo,
                BorderLayout.WEST
        );


        JPanel filtros =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );


        filtros.setOpaque(
                false
        );


        cmbClienteHistorial =
                crearComboTexto();


        cmbClienteHistorial.setPreferredSize(
                new Dimension(
                        250,
                        34
                )
        );


        cmbClienteHistorial.addActionListener(
                e -> actualizarHistorial()
        );


        JButton btnActualizar =
                new JButton(
                        "Actualizar"
                );


        btnActualizar.addActionListener(
                e -> actualizarHistorial()
        );


        filtros.add(
                crearEtiqueta(
                        "Cliente"
                )
        );

        filtros.add(
                cmbClienteHistorial
        );

        filtros.add(
                btnActualizar
        );


        superior.add(
                filtros,
                BorderLayout.EAST
        );


        tarjeta.add(
                superior,
                BorderLayout.NORTH
        );


        modeloHistorial =
                new DefaultTableModel(
                        new Object[]{
                                "Código",
                                "Fecha",
                                "Tipo",
                                "Monto",
                                "Comisión",
                                "Origen",
                                "Destino",
                                "Categoría",
                                "Puntos",
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


        JTable tabla =
                new JTable(
                        modeloHistorial
                );


        tabla.setRowHeight(
                27
        );

        tabla.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );


        tabla.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );


        tabla.getTableHeader()
                .setReorderingAllowed(
                        false
                );


        tabla.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );


        tabla.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(85);

        tabla.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(125);

        tabla.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(115);

        tabla.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(100);

        tabla.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(90);

        tabla.getColumnModel()
                .getColumn(5)
                .setPreferredWidth(125);

        tabla.getColumnModel()
                .getColumn(6)
                .setPreferredWidth(125);

        tabla.getColumnModel()
                .getColumn(7)
                .setPreferredWidth(110);

        tabla.getColumnModel()
                .getColumn(8)
                .setPreferredWidth(80);

        tabla.getColumnModel()
                .getColumn(9)
                .setPreferredWidth(90);


        JScrollPane scroll =
                new JScrollPane(
                        tabla
                );


        scroll.setBorder(
                new EmptyBorder(
                        12,
                        0,
                        0,
                        0
                )
        );


        tarjeta.add(
                scroll,
                BorderLayout.CENTER
        );


        return tarjeta;
    }
    