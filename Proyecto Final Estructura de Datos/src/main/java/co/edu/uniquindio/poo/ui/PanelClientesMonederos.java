package co.edu.uniquindio.poo.ui;

import co.edu.uniquindio.poo.MonederoVirtual;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Monedero;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

public class PanelClientesMonederos extends JPanel {

    // ---------------------------------------------------------
    // COLORES
    // ---------------------------------------------------------

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


    // ---------------------------------------------------------
    // SISTEMA
    // ---------------------------------------------------------

    private final MonederoVirtual sistema;

    private final Runnable alActualizarDatos;


    // ---------------------------------------------------------
    // REGISTRO CLIENTE
    // ---------------------------------------------------------

    private JTextField txtIdentificacion;

    private JTextField txtNombre;


    // ---------------------------------------------------------
    // CREAR MONEDERO
    // ---------------------------------------------------------

    private JComboBox<String> cmbClienteMonedero;

    private JTextField txtIdMonedero;

    private JTextField txtNombreMonedero;

    private JTextField txtSaldoInicial;


    // ---------------------------------------------------------
    // TABLAS
    // ---------------------------------------------------------

    private DefaultTableModel modeloClientes;

    private DefaultTableModel modeloMonederos;


    // ---------------------------------------------------------
    // CONSTRUCTOR
    // ---------------------------------------------------------

    public PanelClientesMonederos(
            MonederoVirtual sistema,
            Runnable alActualizarDatos) {

        this.sistema =
                sistema;

        this.alActualizarDatos =
                alActualizarDatos;

        configurarPanel();

        construirInterfaz();

        actualizarDatos();
    }


    // ---------------------------------------------------------
    // CONFIGURAR PANEL
    // ---------------------------------------------------------

    private void configurarPanel() {

        setLayout(
                new BorderLayout(
                        0,
                        22
                )
        );

        setBackground(
                FONDO
        );

        setBorder(
                new EmptyBorder(
                        25,
                        32,
                        30,
                        32
                )
        );
    }


    // ---------------------------------------------------------
    // CONSTRUIR INTERFAZ
    // ---------------------------------------------------------

    private void construirInterfaz() {

        JPanel formularios =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                0
                        )
                );

        formularios.setOpaque(
                false
        );


        formularios.add(
                crearFormularioCliente()
        );


        formularios.add(
                crearFormularioMonedero()
        );


        add(
                formularios,
                BorderLayout.NORTH
        );


        JPanel tablas =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                0
                        )
                );

        tablas.setOpaque(
                false
        );


        tablas.add(
                crearTablaClientes()
        );


        tablas.add(
                crearTablaMonederos()
        );


        add(
                tablas,
                BorderLayout.CENTER
        );
    }


    // ---------------------------------------------------------
    // FORMULARIO CLIENTE
    // ---------------------------------------------------------

    private JPanel crearFormularioCliente() {

        JPanel tarjeta =
                crearTarjeta();


        JPanel contenido =
                new JPanel(
                        new GridBagLayout()
                );

        contenido.setOpaque(
                false
        );


        GridBagConstraints gbc =
                crearRestricciones();


        JLabel titulo =
                crearTitulo(
                        "Registrar cliente"
                );


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        contenido.add(
                titulo,
                gbc
        );


        gbc.gridwidth = 1;


        // -----------------------------------------------------
        // IDENTIFICACIÓN
        // -----------------------------------------------------

        gbc.gridy++;
        gbc.gridx = 0;

        contenido.add(
                crearEtiqueta(
                        "Identificación"
                ),
                gbc
        );


        txtIdentificacion =
                crearCampoTexto();


        gbc.gridx = 1;

        contenido.add(
                txtIdentificacion,
                gbc
        );


        // -----------------------------------------------------
        // NOMBRE
        // -----------------------------------------------------

        gbc.gridy++;
        gbc.gridx = 0;

        contenido.add(
                crearEtiqueta(
                        "Nombre"
                ),
                gbc
        );


        txtNombre =
                crearCampoTexto();


        gbc.gridx = 1;

        contenido.add(
                txtNombre,
                gbc
        );


        // -----------------------------------------------------
        // BOTÓN
        // -----------------------------------------------------

        JButton btnRegistrar =
                crearBotonPrincipal(
                        "Registrar cliente"
                );


        btnRegistrar.addActionListener(
                e -> registrarCliente()
        );


        gbc.gridy++;
        gbc.gridx = 1;
        gbc.insets =
                new Insets(
                        16,
                        8,
                        5,
                        0
                );

        contenido.add(
                btnRegistrar,
                gbc
        );


        tarjeta.add(
                contenido,
                BorderLayout.CENTER
        );


        return tarjeta;
    }


    // ---------------------------------------------------------
    // FORMULARIO MONEDERO
    // ---------------------------------------------------------

    private JPanel crearFormularioMonedero() {

        JPanel tarjeta =
                crearTarjeta();


        JPanel contenido =
                new JPanel(
                        new GridBagLayout()
                );

        contenido.setOpaque(
                false
        );


        GridBagConstraints gbc =
                crearRestricciones();


        JLabel titulo =
                crearTitulo(
                        "Crear monedero"
                );


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        contenido.add(
                titulo,
                gbc
        );


        gbc.gridwidth = 1;


        // -----------------------------------------------------
        // CLIENTE
        // -----------------------------------------------------

        gbc.gridy++;
        gbc.gridx = 0;

        contenido.add(
                crearEtiqueta(
                        "Cliente"
                ),
                gbc
        );


        cmbClienteMonedero =
                new JComboBox<>();

        configurarCombo(
                cmbClienteMonedero
        );


        gbc.gridx = 1;

        contenido.add(
                cmbClienteMonedero,
                gbc
        );


        // -----------------------------------------------------
        // ID MONEDERO
        // -----------------------------------------------------

        gbc.gridy++;
        gbc.gridx = 0;

        contenido.add(
                crearEtiqueta(
                        "Código"
                ),
                gbc
        );


        txtIdMonedero =
                crearCampoTexto();


        gbc.gridx = 1;

        contenido.add(
                txtIdMonedero,
                gbc
        );


        // -----------------------------------------------------
        // NOMBRE MONEDERO
        // -----------------------------------------------------

        gbc.gridy++;
        gbc.gridx = 0;

        contenido.add(
                crearEtiqueta(
                        "Nombre"
                ),
                gbc
        );


        txtNombreMonedero =
                crearCampoTexto();


        gbc.gridx = 1;

        contenido.add(
                txtNombreMonedero,
                gbc
        );


        // -----------------------------------------------------
        // SALDO
        // -----------------------------------------------------

        gbc.gridy++;
        gbc.gridx = 0;

        contenido.add(
                crearEtiqueta(
                        "Saldo inicial"
                ),
                gbc
        );


        txtSaldoInicial =
                crearCampoTexto();

        txtSaldoInicial.setText(
                "0"
        );


        gbc.gridx = 1;

        contenido.add(
                txtSaldoInicial,
                gbc
        );


        // -----------------------------------------------------
        // BOTÓN
        // -----------------------------------------------------

        JButton btnCrear =
                crearBotonPrincipal(
                        "Crear monedero"
                );


        btnCrear.addActionListener(
                e -> crearMonedero()
        );


        gbc.gridy++;
        gbc.gridx = 1;
        gbc.insets =
                new Insets(
                        16,
                        8,
                        5,
                        0
                );

        contenido.add(
                btnCrear,
                gbc
        );


        tarjeta.add(
                contenido,
                BorderLayout.CENTER
        );


        return tarjeta;
    }


    // ---------------------------------------------------------
    // TABLA CLIENTES
    // ---------------------------------------------------------

    private JPanel crearTablaClientes() {

        modeloClientes =
                new DefaultTableModel(
                        new Object[]{
                                "Identificación",
                                "Nombre",
                                "Puntos",
                                "Rango",
                                "Monederos"
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
                crearTabla(
                        modeloClientes
                );


        return crearPanelTabla(
                "Clientes registrados",
                tabla
        );
    }


    // ---------------------------------------------------------
    // TABLA MONEDEROS
    // ---------------------------------------------------------

    private JPanel crearTablaMonederos() {

        modeloMonederos =
                new DefaultTableModel(
                        new Object[]{
                                "Cliente",
                                "Código",
                                "Nombre",
                                "Saldo"
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
                crearTabla(
                        modeloMonederos
                );


        return crearPanelTabla(
                "Monederos registrados",
                tabla
        );
    }


    // ---------------------------------------------------------
    // REGISTRAR CLIENTE
    // ---------------------------------------------------------

    private void registrarCliente() {

        String identificacion =
                txtIdentificacion
                        .getText()
                        .trim();


        String nombre =
                txtNombre
                        .getText()
                        .trim();


        if (identificacion.isEmpty() ||
                nombre.isEmpty()) {

            mostrarAdvertencia(
                    "Debe ingresar la identificación y el nombre."
            );

            return;
        }


        Cliente cliente =
                new Cliente(
                        identificacion,
                        nombre
                );


        boolean registrado =
                sistema.registrarCliente(
                        cliente
                );


        if (!registrado) {

            mostrarError(
                    "No fue posible registrar el cliente.\n"
                            +
                            "Verifique que la identificación no esté repetida."
            );

            return;
        }


        txtIdentificacion.setText(
                ""
        );

        txtNombre.setText(
                ""
        );


        actualizarDatos();

        notificarCambioGeneral();


        mostrarExito(
                "Cliente registrado correctamente."
        );
    }


    // ---------------------------------------------------------
    // CREAR MONEDERO
    // ---------------------------------------------------------

    private void crearMonedero() {

        Object clienteSeleccionado =
                cmbClienteMonedero
                        .getSelectedItem();


        if (clienteSeleccionado == null) {

            mostrarAdvertencia(
                    "Primero debe registrar un cliente."
            );

            return;
        }


        String identificacionCliente =
                clienteSeleccionado
                        .toString();


        String idMonedero =
                txtIdMonedero
                        .getText()
                        .trim();


        String nombre =
                txtNombreMonedero
                        .getText()
                        .trim();


        String textoSaldo =
                txtSaldoInicial
                        .getText()
                        .trim();


        if (idMonedero.isEmpty() ||
                nombre.isEmpty()) {

            mostrarAdvertencia(
                    "Debe ingresar el código y el nombre del monedero."
            );

            return;
        }


        double saldoInicial;


        try {

            if (textoSaldo.isEmpty()) {

                saldoInicial = 0;

            } else {

                saldoInicial =
                        Double.parseDouble(
                                textoSaldo.replace(
                                        ",",
                                        "."
                                )
                        );
            }

        } catch (NumberFormatException e) {

            mostrarAdvertencia(
                    "El saldo inicial debe ser un número válido."
            );

            return;
        }


        if (saldoInicial < 0) {

            mostrarAdvertencia(
                    "El saldo inicial no puede ser negativo."
            );

            return;
        }


        Monedero monedero =
                new Monedero(
                        idMonedero,
                        nombre,
                        saldoInicial
                );


        boolean creado =
                sistema.crearMonedero(
                        identificacionCliente,
                        monedero
                );


        if (!creado) {

            mostrarError(
                    "No fue posible crear el monedero.\n"
                            +
                            "Verifique que el código no esté repetido para este cliente."
            );

            return;
        }


        txtIdMonedero.setText(
                ""
        );

        txtNombreMonedero.setText(
                ""
        );

        txtSaldoInicial.setText(
                "0"
        );


        actualizarDatos();

        notificarCambioGeneral();


        mostrarExito(
                "Monedero creado correctamente."
        );
    }


    // ---------------------------------------------------------
    // ACTUALIZAR INFORMACIÓN
    // ---------------------------------------------------------

    public void actualizarDatos() {

        actualizarComboClientes();

        actualizarTablaClientes();

        actualizarTablaMonederos();
    }
    
