package GrattaEVinci2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.*;
import java.io.File;

class DrawPad extends JComponent {
    BufferedImage image;
    BufferedImage card_surface;

    Graphics2D graphics2D;
    int currentX, currentY, oldX, oldY;



    public DrawPad() {
        final Stroke stroke = new BasicStroke (17.0F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);

        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                if (graphics2D != null){
                    graphics2D.setStroke(stroke);
                    graphics2D.setPaint(Color.gray);
                    graphics2D.drawLine(oldX, oldY, currentX, currentY);
                    repaint();
                    oldX = currentX;
                    oldY = currentY;
                }
            }
        });
    }
    /*private AlphaComposite makeComposite(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    }*/
    private Image TransformColorToTransparency(BufferedImage image, Color c1, Color c2)
    {
        final int r1 = c1.getRed();
        final int g1 = c1.getGreen();
        final int b1 = c1.getBlue();
        final int r2 = c2.getRed();
        final int g2 = c2.getGreen();
        final int b2 = c2.getBlue();
        ImageFilter filter = new RGBImageFilter()
        {
            public final int filterRGB(int x, int y, int rgb)
            {
                int r = (rgb & 0xFF0000) >> 16;
                int g = (rgb & 0xFF00) >> 8;
                int b = rgb & 0xFF;
                if (r >= r1 && r <= r2 && g >= g1 && g <= g2 && b >= b1 && b <= b2)
                {
                    return rgb & 0xFFFFFF;
                }
                return rgb;
            }
        };

        ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }

    private BufferedImage ImageToBufferedImage(Image image2, int width, int height)
    {
        BufferedImage immage = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D grafich2d = immage.createGraphics();
        grafich2d.drawImage(image2, 0, 0, null);
        return immage;
    }
    public void clear() {
        image=null;
        card_surface=null;
        repaint();
    }
    public void paintComponent(Graphics g) {
        String imagePath=null;
        String imagePath2=null;
        int numero= Main.numero;
        int immagine=0;
        if (image == null) {
            image = new BufferedImage(500, 300, BufferedImage.TYPE_INT_ARGB);
            if(numero<=7) {
                immagine=(int) (Math.random() *3)+1;
                if(numero==1) {
                    switch (immagine) {
                        case 1:
                            imagePath = "GRATTA E VINCI VINCENTE 1.png";
                            break;
                        case 2:
                            imagePath = "GRATTA E VINCI VINCENTE 2.png";
                            break;
                        case 3:
                            imagePath = "GRATTA E VINCI VINCENTE 3.png";
                            break;
                    }
                }
                else {
                    switch(numero) {
                        case 2:
                            imagePath = "GRATTA E VINCI VINCENTE   SECONDA VINCITA.png";
                            break;
                        case 3:
                            imagePath = "GRATTA E VINCI VINCENTE  TERZA VINCITA.png";
                            break;
                        case 4:
                            imagePath = "GRATTA E VINCI VINCENTE  QUARTA VINCITA.png";
                            break;
                        case 5:
                            imagePath = "GRATTA E VINCI VINCENTE  QUINTA VINCITA";
                            break;
                        case 6:
                            imagePath = "GRATTA E VINCI VINCENTE  SESTA VINCITA.png";
                            break;
                        case 7:
                            imagePath = "GRATTA E VINCI VINCENTE  SETTIMA VINCITA.png";
                            break;
                    }
                }
                File inFile = new File(imagePath);

                try{
                    image = ImageIO.read(inFile);
                }catch(java.io.IOException e){System.out.println(e);}
            }
            else{
                immagine=(int) (Math.random() *6)+1;

                switch (immagine) {
                    case 1:
                        imagePath = "GRATTA E VINCI PERDENTE.png";
                        break;
                    case 2:
                        imagePath = "GRATTA E VINCI PERDENTE 2.png";
                        break;
                    case 3:
                        imagePath = "GRATTA E VINCI PERDENTE 3.png";
                        break;
                    case 4:
                        imagePath = "GRATTA E VINCI PERDENTE 4.png";
                        break;
                    case 5:
                        imagePath = "GRATTA E VINCI PERDENTE 5.png";
                        break;
                    case 6:
                        imagePath = "GRATTA E VINCI PERDENTE 6.png";
                        break;
                }
                File inFile = new File(imagePath);
                try{
                    image = ImageIO.read(inFile);
                }catch(java.io.IOException e){System.out.println(e);}
            }
            //System.out.println(immagine+"\n");
            //System.out.println(imagePath+"\n");

            graphics2D = image.createGraphics();

            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            repaint();
        }

        if (card_surface == null) {
            card_surface = new BufferedImage(500, 300, BufferedImage.TYPE_INT_ARGB);

            imagePath2 = "GRATTA E VINCI BASE.png";
            File inFile2 = new File(imagePath2);

            try{
                card_surface = ImageIO.read(inFile2);
            }catch(java.io.IOException e){System.out.println(e);}

            graphics2D = (Graphics2D) card_surface.getGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


            repaint();
        }


        g.drawImage(image, 0, 0, null);
        g.drawImage(card_surface, 0, 0, null);
        Image transpImg2 =TransformColorToTransparency(card_surface, new Color(0, 50, 77), new Color(200, 200, 255));
        BufferedImage resultImage2 = ImageToBufferedImage(transpImg2, image.getWidth(), image.getHeight());
        g.drawImage(image, 0, 0, null);
        g.drawImage(resultImage2, 0, 0, null);
    }
}