public class NBody {

	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	
	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[num];
		for(int i = 0; i < num; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			planets[i] = planet;
		} 
		return planets;
	}

	public static void main(String[] args) {
		
		//读取一些信息
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String fileName = args[2];
		double radius = readRadius(fileName);
		Planet[] planets = readPlanets(fileName);
	
		//画背景
		String imageToDraw = "images/starfield.jpg";
		StdDraw.setScale(-1 * radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);
		StdDraw.enableDoubleBuffering();

		//开始咯
		int l = planets.length;
		double time = 0;
		while(time < T) {
			double xForces[] = new double[l];
			double yForces[] = new double[l];
			for(int i = 0; i < l; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);		
			}
			for(int i = 0; i < l; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, imageToDraw);
			for(int i = 0; i < l; i++) {
				StdDraw.picture(planets[i].xxPos, planets[i].yyPos, "images/" + planets[i].imgFileName);
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            		      planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  		planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
		
	}

}