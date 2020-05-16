import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 *
 * @author Kevin Lin
 * @version 2020-0421
 */
public class Fireball extends Projectile
{
    /**
     *
     */
    private int imgNum = 1;
    private boolean animationComplete = false;
    GreenfootImage [] imgs = new GreenfootImage[38];
    public void act()
    {
        update();
    }
    //negative diretion to launch left, positve to launch right. Any integer value
    //power, angle, explosion height and gravity can be changed
    /**
     * @param power The power which the fireball is launched
     * @param theta The angle which the fireball is launched
     * @param dir The direction which the fireball is launched
     * @param exploHeight The height at which the fireball explosdes
     * @param grav The gravitation constant
     */
    public Fireball(int power, int theta, int dir, int exploHeight, int grav){
        gravity = grav;
        speed = power;
        angle = theta;
        direction = dir;
        height = exploHeight;
        defineV(speed,angle);
        setImg();
    }
    /**
     * @param power The power which the fireball is launched
     * @param theta The angle which the fireball is launched
     * @param dir The direction which the fireball is launched
     * @param exploHeight The height at which the fireball explosdes
     */
    public Fireball(int power, int theta, int dir, int exploHeight){
        gravity = 10;
        speed = power;
        angle = theta;
        direction = dir;
        height = exploHeight;
        defineV(speed,angle);
        setImg();
    }
    /**
     * @param power The power which the fireball is launched
     * @param theta The angle which the fireball is launched
     * @param dir The direction which the fireball is launched
     */
    public Fireball(int power, int theta, int dir){
        gravity = 10;
        speed = power;
        angle = theta;
        direction = dir;
        height = 10;
        defineV(speed,angle);
        setImg();
    }
    /**
     * @param dir The direction which the fireball is launched
     */
    public Fireball(int dir){
        speed = 100;
        angle = 20;
        defineV(speed,angle);
        this.direction = dir;
        setImg();
    }
    //defining the x and y axis velocity

    private void defineV(int speed, int angle){
        vX = (double)speed * Math.cos((double)angle*Math.PI/180.0) ;
        vY = -(double)speed * Math.sin((double)angle*Math.PI/180.0);
    }

    /**
     * This method updates the location of the fireball, and detects where to explode
     */
    public void update(){
        if(direction >= 0){
            setLocation(getX() + (int)vX, getY() + (int)vY);
        }else{
            setLocation(getX() - (int)vX, getY() + (int)vY);
        }

        if(getY() > getWorld().getHeight() - height){
            vX = 0;
            vY = 0;
            animate();
        }else{
            vY+= gravity;
        }
        checkGround();
    }

    //remove object once animation is complete
    public void checkGround(){
        if(animationComplete){
            remove();
        }
    }

    //loading images into array
    private void setImg(){
        for(int i = 2; i < 40; i++){
            imgs[i-2] = new GreenfootImage( "exp " + i + ".png");
        }
        setImage( imgs[imgNum] );
    }

    //cycle through images for animation, and set animation completion to true
    private void animate(){
        imgNum = ( imgNum + 1 ) % imgs.length;
        getImage().scale(500,500);
        setImage( imgs[imgNum] );
        if(imgNum >= 37){
            animationComplete = true;
        }
    }
}
