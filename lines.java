import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите координаты линий в формате x1 y1 x2 y2 ... или (x1;y1); (x2;y2); ... или (x1,y1,x2,y2) ...");

        Scanner scanner = new Scanner(System.in);
        String self = scanner.nextLine();
        self = self.replaceAll(";|,", " ").replaceAll(".&&[^\\s\\d]", "").replaceAll("\\p{P}", "");
        scanner = new Scanner(self);
        List < Line > arrayList = new ArrayList < > ();
        Line line;
        while (true) {
            try {
                line = new Line(new Point(scanner.nextInt(), scanner.nextInt()), new Point(scanner.nextInt(), scanner.nextInt()));
            } catch (InputMismatchException ex) {
                break;
            } catch (NoSuchElementException ex) {
                break;
            }
            arrayList.add(line);
        }

        for (Line i: arrayList) {
            System.out.println(i);
            System.out.println("Расстояние между точками начала и конца: " + i.getLength() + "\n");
        }

        System.out.println("Введите номер линии, точки и её новые координаты через пробел: ");
        scanner = new Scanner(System.in);
        int ln = scanner.nextInt() - 1;
        int pt = scanner.nextInt();

        switch (pt) {
            case 1:
                arrayList.get(ln).setStartPoint(new Point(scanner.nextInt(), scanner.nextInt()));
                break;
            case 2:
                arrayList.get(ln).setEndPoint(new Point(scanner.nextInt(), scanner.nextInt()));
                break;
        }

        System.out.println("\nВведите номер линии и точки, чтобы вывести координаты: ");
        scanner = new Scanner(System.in);
        ln = scanner.nextInt() - 1;
        pt = scanner.nextInt();

        switch (pt) {
            case 1:
                System.out.println(arrayList.get(ln).getStartPoint());
                break;
            case 2:
                System.out.println(arrayList.get(ln).getEndPoint());
                break;
        }
    }
}

class Point {
    private int x = 0, y = 0;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" + x + ";" + y + "}";
    }
}

class Line {
    private Point beginPoint, endPoint;

    public Line(Point p1, Point p2) {
        this.beginPoint = new Point(p1.getX(), p1.getY());
        this.endPoint = new Point(p2.getX(), p2.getY());
    }

    public Line(int x1, int y1, int x2, int y2) {
        this.beginPoint = new Point(x1, y1);
        this.endPoint = new Point(x2, y2);
    }

    public Point getStartPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setStartPoint(Point p) {
        beginPoint = new Point(p.getX(), p.getY());
    }

    public void setEndPoint(Point p) {
        endPoint = new Point(p.getX(), p.getY());
    }

    public double getLength() {
        int dx = endPoint.getX() - beginPoint.getX();
        int dy = endPoint.getY() - beginPoint.getY();
        return Math.hypot(dx, dy);
    }

    @Override
    public String toString() {
        return "Линия от " + beginPoint + " до " + endPoint;
    }
}