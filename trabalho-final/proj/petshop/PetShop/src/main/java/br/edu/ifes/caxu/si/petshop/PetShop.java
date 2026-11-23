package br.edu.ifes.caxu.si.petshop;

import br.edu.ifes.caxu.si.petshop.view.FrameTabelaPet;
import br.edu.ifes.caxu.si.petshop.view.FrameTabelaResponsaveis;

/**
 *
 * @author alexandre
 */
public class PetShop {

  public static void main(String[] args) {
    java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrameTabelaPet.class.getName());
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
      logger.log(java.util.logging.Level.SEVERE, null, ex);
    }
    java.awt.EventQueue.invokeLater(() -> new FrameTabelaPet().setVisible(true));
  }
}
