package com.pooproject.products_system.views;

import javax.swing.*;
import java.awt.*;

public class AboutView extends JFrame {

    public AboutView() {
        setTitle("Sobre");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));

        JLabel titleLabel = new JLabel("Sistema Mercadinho", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel);

        panel.add(new JLabel("Desenvolvido em: 2024", SwingConstants.CENTER));
        panel.add(new JLabel("Versão: 1.0.1", SwingConstants.CENTER));

        JLabel descriptionTitleLabel = new JLabel("Descrição:", SwingConstants.CENTER);
        descriptionTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(descriptionTitleLabel);

        panel.add(new JLabel("O Mercadinho é um sistema de gerenciamento", SwingConstants.CENTER));
        panel.add(new JLabel("criado para atender pequenas empresas do setor varejista.", SwingConstants.CENTER));
        panel.add(new JLabel("O programa oferece uma solução prática e eficiente,", SwingConstants.CENTER));
        panel.add(new JLabel("permitindo o cadastro de clientes, produtos e vendas.", SwingConstants.CENTER));
        panel.add(new JLabel("Além disso, garante maior confiabilidade e facilita auditorias.", SwingConstants.CENTER));

        JLabel devTitleLabel = new JLabel("Desenvolvedores:", SwingConstants.CENTER);
        devTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(devTitleLabel);

        panel.add(new JLabel("Pedro Henrique da Silva Novais", SwingConstants.CENTER));
        panel.add(new JLabel("Pedro Henrique Sacramento Carvalho", SwingConstants.CENTER));
        panel.add(new JLabel("Joao Vitor Canella Rodrigues de Mesquita", SwingConstants.CENTER));
        panel.add(new JLabel("Victor Vianna de Souza", SwingConstants.CENTER));
        panel.add(new JLabel("Luiz Henrique de Oliveira Duque", SwingConstants.CENTER));

        JLabel docenteTitleLabel = new JLabel("Docente:", SwingConstants.CENTER);
        docenteTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(docenteTitleLabel);

        panel.add(new JLabel("Lazaro Pereira de Oliveira", SwingConstants.CENTER));

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AboutView::new);
    }
}



