class Planet {
	
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		return Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + 
			             (yyPos - p.yyPos) * (yyPos - p.yyPos));
	}

	public double calcForceExertedBy(Planet p) {
		double G = 667e-13;
		double distance = this.calcDistance(p);
		return (G * mass * p.mass) / (distance * distance);
	}
	
	public double calcForceExertedByX(Planet p) {
		double totalForce = this.calcForceExertedBy(p);
		double distance = this.calcDistance(p);
		return totalForce * (p.xxPos - xxPos) / distance;
	}

	public double calcForceExertedByY(Planet p) {
		double totalForce = this.calcForceExertedBy(p);
		double distance = this.calcDistance(p);
		return totalForce * (p.yyPos - yyPos) / distance;
	}

	public double calcNetForceExertedByX(Planet[] allP) {
		double totalForce = 0;
		int l = allP.length;
		for(int i = 0; i < l; i++) {
			if(!this.equals(allP[i])) {
				totalForce += this.calcForceExertedByX(allP[i]);
			}
		}
		return totalForce;
	}

	public double calcNetForceExertedByY(Planet[] allP) {
		double totalForce = 0;
		int l = allP.length;
		for(int i = 0; i < l; i++) {
			if(!this.equals(allP[i])) {
				totalForce += this.calcForceExertedByY(allP[i]);
			}
		}
		return totalForce;
	}

	public void update(double t, double fx, double fy) {
		double ax = fx / mass;
		double ay = fy / mass;
		xxVel += ax * t;
		yyVel += ay * t;
		xxPos += xxVel * t;
		yyPos += yyVel * t;
		return;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
		//StdDraw.show();
		return;
	}

}