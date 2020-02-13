package src.main.com;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Añadir jugador");
        System.out.println("2. Mostrar procedencia y posición de los jugadores de los Cavaliers.");
        System.out.println("3. Mostrar número de jugadores españoles.");
        System.out.println("4. Mostrar jugadores que en la temporada 04/05 tenían una media de puntos por partido superior a 10.");
        switch (sc.nextInt()) {
            case 1:
                JugadoresEntity jugadores = new JugadoresEntity();
                jugadores.setCodigo(700);
                jugadores.setNombre("Pepe");
                jugadores.setAltura("2");
                jugadores.setPeso(90);
                jugadores.setPosicion("Alero");
                jugadores.setProcedencia("From Mexicoooooo");
                session.save(jugadores);

                session.getTransaction().commit();
                HibernateUtil.shutdown();
            case 2:
                List<JugadoresEntity> jugadoresEntities =session.createQuery("SELECT a FROM JugadoresEntity AS a WHERE nombre_equipo='Cavaliers'").list();

                System.out.println("----JUGADORES DE LOS CAVALIERS----");
                for (JugadoresEntity a:jugadoresEntities) {
                    System.out.println("La Procedencia es: "+a.getProcedencia()+" Su posicion es: "+a.getPosicion());
                }
            case 3:
                List<JugadoresEntity> jugadoresEntities1 = session.createQuery("SELECT a FROM JugadoresEntity AS a WHERE procedencia='Spain'").list();
                System.out.println(jugadoresEntities1.size());
            case 4:
                List<EstadisticasEntity> estadisticasEntities = session.createQuery("FROM EstadisticasEntity WHERE temporada='04/05' AND puntosPorPartido>10").list();
                List<JugadoresEntity> jugadoresEntities2 = session.createQuery("FROM JugadoresEntity ").list();

                int i = 0;
                for (JugadoresEntity a:jugadoresEntities2) {
                    for (EstadisticasEntity b:estadisticasEntities) {
                        if (b.getJugador()==a.getCodigo()) {
                            System.out.println(a.getNombre()+" || "+b.getPuntosPorPartido()+" || "+b.getTemporada());
                        }
                    }
                }
        }

        // Add new Employee object

    }
}
