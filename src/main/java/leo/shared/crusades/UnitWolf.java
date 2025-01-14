///////////////////////////////////////////////////////////////////////
// Name: UnitWolf
// Desc: Growl snarl growl
// Date: 7/8/2003 - Gabe Jones
// TODO:
///////////////////////////////////////////////////////////////////////
package leo.shared.crusades;

// imports

import leo.shared.*;

import java.util.Vector;


public class UnitWolf extends Unit {

    /////////////////////////////////////////////////////////////////
    // Properties
    /////////////////////////////////////////////////////////////////
    private boolean summoned = false;
    private Unit parent = null;

    /////////////////////////////////////////////////////////////////
    // Constructor
    /////////////////////////////////////////////////////////////////
    public UnitWolf(Castle newCastle) {
        castle = newCastle;

        // Initialize
        id = Unit.WOLF;
        name = Strings.UNIT_WOLF_1;
        actions = new Vector<Action>();
        damage = 3;
        armor = 0;
        life = 4;
        lifeMax = 4;
        actionsLeft = 2;
        actionsMax = 2;
        move = new ActionMove(this, (byte) 0, (byte) 1, TargetType.LOCATION_LINE_JUMP, (byte) 2);
        attack = new ActionAttack(this, (byte) 0, (byte) 1, TargetType.UNIT_LINE, (byte) 1);
        deployCost = 1;
        castleCost = 1001;
        organic = true;
        appearance = Constants.IMG_WOLF;

        // Pounce event
        EventPounce ep = new EventPounce(this);
        add((Event) ep);
        add((Action) ep);

        // Add the actions
        actions.add(move);
        actions.add(attack);
    }

    public void summoned() {
        summoned = true;
    }

    public boolean isSummoned() {
        return summoned;
    }

    public void setParent(Unit p) {
        parent = p;
    }

    public void entered() {
        if (id == Unit.NONE)
            die(false, this);
        else if (parent != null)
            id = Unit.NONE;
    }

    /////////////////////////////////////////////////////////////////
    // force a false death, acts as a summon when summoned by druid
    /////////////////////////////////////////////////////////////////
    public void die(boolean death, Unit source) {
        if (id == Unit.NONE) {
            if (parent == null) {
                System.out.println("Invalid summon death: no parent");
                return;
            }
            EventSummoner esum = parent.getSummonManager();
            if (esum == null) {
                System.out.println("Invalid summon death");
                return;
            }
            esum.perform(this, source, Event.NONE, Event.NONE, Event.NONE);

            getBattleField().remove(this);
            getCastle().removeOut(this);
            deathTrigger(death, source);

            if (death) {
                if (getOrganic(this)) getCastle().addGraveyard(this);
                if (getOrganic(this)) source.setMurderer();
                //getCastle().getObserver().death(this, source);
            }
        } else {
            super.die(death, source);
        }
    }

}
