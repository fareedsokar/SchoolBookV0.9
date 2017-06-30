package Teacher;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.swing.tree.*;


public class TeacherMenuUI {
  public JTree tree = new JTree();
			
  public JComponent makeUI() {
    IconUIResource emptyIcon = new IconUIResource(new Icon() {
      @Override public void paintIcon(Component c, Graphics g, int x, int y) {}
      @Override public int getIconWidth() {
        return 0;
      }
      @Override public int getIconHeight() {
        return 0;
      }
    });
    //UIManager.put("Tree.editorBorder", BorderFactory.createLineBorder(Color.black, 0));
    UIManager.put("Tree.expandedIcon",  emptyIcon);
    UIManager.put("ScrollPane.border", BorderFactory.createEmptyBorder());
    //UIManager.put("Tree.paintLines", Boolean.FALSE);
    UIManager.put("Tree.collapsedIcon", emptyIcon);
    //UIManager.put("Tree.closedIcon",    emptyIcon);
    //UIManager.put("Tree.openIcon",      emptyIcon);
    UIManager.put("Tree.paintLines",    Boolean.FALSE);

    UIDefaults d = new UIDefaults();
    d.put("Tree:TreeCell[Enabled+Selected].backgroundPainter", new Painter<JComponent>() {
      @Override public void paint(Graphics2D g, JComponent c, int w, int h) {
        g.setPaint(Color.GREEN.darker());
        g.fillRect(0, 0, w, h);
      }
    });

    tree = new JTree(makeModel());
    
    //tree.setBorder( BorderFactory.createEmptyBorder());
    tree.setCellRenderer(new TestTreeCellRenderer());
    //tree.setRowHeight(0);
    //tree.gese
    tree.setRootVisible(false);
    //tree.setBorder(null);
    tree.setShowsRootHandles(false);
    tree.setBackground(Color.WHITE);
    tree.putClientProperty("Nimbus.Overrides", d);
    //tree.putClientProperty("Window.Overrides",d);
    //tree.putClientProperty("Metal.Overrides",d);
    //tree.putClientProperty("Metal.Overrides.InheritDefaults", false);
    //tree.putClientProperty("Window.Overrides.InheritDefault", false);
    tree.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
    JScrollPane jsp = new JScrollPane(tree);
    jsp.setBorder(null);
    jsp.setForeground(SystemColor.activeCaption);
    //jsp.setViewportBorder(null);
    //unistallBorder(jsco);
    
    return jsp;
    
  }

  public static TreeModel makeModel() {
    Dimension d64 = new Dimension(24, 24);
    Dimension d32 = new Dimension(16, 16);
    Dimension d24 = new Dimension(8, 8);
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
    DefaultMutableTreeNode set1 = new DefaultMutableTreeNode(
        new TestNode("Information", Color.ORANGE, d64, false));
    set1.add(new DefaultMutableTreeNode(
        new TestNode("Working Hours", Color.ORANGE, d32, true)));
    set1.add(new DefaultMutableTreeNode(
        new TestNode("Show Courses", Color.CYAN.darker(), d32, true)));
    set1.add(new DefaultMutableTreeNode(
        new TestNode("Define Pre Courses", Color.GREEN, d32, true)));
    DefaultMutableTreeNode set2 = new DefaultMutableTreeNode(
        new TestNode("Requests", Color.GREEN.darker().darker(), d64, false));
    DefaultMutableTreeNode set3 = new DefaultMutableTreeNode(
        new TestNode("Changing Request", Color.GREEN, d32, false));
    /*
    set3.add(new DefaultMutableTreeNode(
        new TestNode("SubSubSubCategory 1", Color.BLUE, d24, true)));
    set3.add(new DefaultMutableTreeNode(
        new TestNode("SubSubSubCategory 2", Color.GREEN, d24, true)));
    set3.add(new DefaultMutableTreeNode(
        new TestNode("SubSubSubCategory 3", Color.ORANGE.darker(), d24, true)));
        */
    
    DefaultMutableTreeNode set4 = new DefaultMutableTreeNode(
            new TestNode("Assigment&Materials", Color.BLUE.darker().darker(), d64, false));
    set2.add(new DefaultMutableTreeNode(
        new TestNode("Teach Unit", Color.ORANGE.darker().darker(), d32, true)));
    set4.add(new DefaultMutableTreeNode(
            new TestNode("Upload Assigment", Color.ORANGE.darker().darker(), d32, true)));
    set4.add(new DefaultMutableTreeNode(
            new TestNode("Upload Materials", Color.LIGHT_GRAY.darker().darker(), d32, true)));
    set4.add(new DefaultMutableTreeNode(
            new TestNode("Show Answers", Color.CYAN.darker().darker(), d32, true)));
    
    DefaultMutableTreeNode set5 = new DefaultMutableTreeNode(
            new TestNode("Grades&Estimate", Color.yellow.darker().darker(), d64, false));
    set5.add(new DefaultMutableTreeNode(
            new TestNode("Estimating File", Color.pink.darker().darker(), d32, true)));
    set2.add(set3);  
    root.add(set1);
    root.add(set2);
    root.add(set4);	
    root.add(set5);	
    return new DefaultTreeModel(root);
  }

  public static void main(String... args) {
    EventQueue.invokeLater(() -> {
      try {
        for (UIManager.LookAndFeelInfo laf: UIManager.getInstalledLookAndFeels()) {
          if ("Metal".equals(laf.getName())) {
            UIManager.setLookAndFeel(laf.getClassName());
          }
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      JFrame f = new JFrame();
      f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      f.getContentPane().add(new TeacherMenuUI().makeUI());
      f.setSize(320, 512);
      f.setLocationRelativeTo(null);
      f.setVisible(true);
    });
  }
}

class TestTreeCellRenderer extends DefaultTreeCellRenderer {
  @Override public Component getTreeCellRendererComponent(
      JTree tree, Object value, boolean selected, boolean expanded,
      boolean leaf, int row, boolean hasFocus) {
    JLabel l = (JLabel) super.getTreeCellRendererComponent(
        tree, value, selected, expanded, leaf, row, hasFocus);
    if (value instanceof DefaultMutableTreeNode) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
      Object uo = node.getUserObject();
      if (uo instanceof TestNode) {
        TestNode i = (TestNode) uo;
        //System.out.print(i.toString() +"\n");
        l.setForeground(Color.BLACK);
        l.setIcon(new TestNode(i.title, i.color, i.dim, leaf, expanded));

        int indent = 0;
        TreeNode parent = node.getParent();
        while (parent instanceof DefaultMutableTreeNode) {
          DefaultMutableTreeNode pn = (DefaultMutableTreeNode) parent;
          if (pn.getUserObject() instanceof TestNode) {
            TestNode pi = (TestNode) pn.getUserObject();
            indent += pi.dim.width / 2;
          }
          parent = pn.getParent();
        }
        l.setBorder(BorderFactory.createEmptyBorder(2, indent, 2, 5));
      }
    }
    //System.out.print(l.getText() + "\n");
    return l;
  }
}

class TestNode implements Icon {
  public final String title;
  public final Color color;
  public final Dimension dim;
  private final boolean expanded;
  private final boolean leaf;
  private static int GAP = 0;
  //public TestNode(String title, ImageIcon img, Dimension dim) {
  public TestNode(String title, Color color, Dimension dim, boolean leaf) {
    this(title, color, dim, leaf, false);
  }
  public TestNode(String title, Color color, Dimension dim,
                  boolean leaf, boolean expanded) {
    this.title = title;
    this.color = color;
    this.dim = dim;
    this.expanded = expanded;
    this.leaf = leaf;
  }
  @Override public void paintIcon(Component c, Graphics g, int x, int y) {
    g.setColor(color);
    g.fillRect(x + GAP, y + GAP, dim.width - GAP - GAP, dim.height - GAP - GAP);
    if (dim.width < 64) {
      Graphics2D g2 = (Graphics2D) g.create();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                          RenderingHints.VALUE_ANTIALIAS_ON);
      int w6 = dim.width / 12;
      int w3 = dim.width / 6;
      g2.setColor(Color.WHITE);
      g2.setStroke(new BasicStroke(w6));
      Point pt = new Point(x + dim.width / 2, y + dim.height / 2);
      Path2D path = new Path2D.Double();
      path.moveTo(pt.x - w6, pt.y - w3);
      path.lineTo(pt.x + w6, pt.y);
      path.lineTo(pt.x - w6, pt.y + w3);
      int numquadrants;
      if (leaf) {
        numquadrants = 0;
      } else if (expanded) {
        numquadrants = 3;
      } else {
        numquadrants = 1;
      }
      AffineTransform at = AffineTransform.getQuadrantRotateInstance(
          numquadrants, pt.x, pt.y);
      g2.draw(at.createTransformedShape(path));
      g2.dispose();
    }
  }
  @Override public int getIconWidth() {
    return dim.width;
  }
  @Override public int getIconHeight() {
    return dim.height;
  }
  @Override public String toString() {
    return title;
  }
}