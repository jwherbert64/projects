package jwherbert64.cosmoverse;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by James on 16/02/2017.
 */

public class ImageAdapterPlanet extends BaseAdapter {
    private Context context;

    public String[] title = {
            "Mercury",
            "Venus",
            "Earth",
            "Mars",
            "Jupiter",
            "Saturn",
            "Uranus",
            "Neptune",
            "Pluto",
            "Moon",
            "Phobos",
            "Ganymede",
            "Io",
            "Titan",
            "Miranda",
            "Triton"
    };

    public String[] images = {
            "@drawable/planet_1",
            "@drawable/planet_2",
            "@drawable/planet_3",
            "@drawable/planet_4",
            "@drawable/planet_5",
            "@drawable/planet_6",
            "@drawable/planet_7",
            "@drawable/planet_8",
            "@drawable/planet_9",
            "@drawable/planet_10",
            "@drawable/planet_11",
            "@drawable/planet_12",
            "@drawable/planet_13",
            "@drawable/planet_14",
            "@drawable/planet_15",
            "@drawable/planet_16"
    };

    public String[] description = {
            "Mercury is the smallest and innermost planet in our Solar System. It's 50 times smaller than earth and has the shortest orbital period of 88 days. It's gravitationally locked to the Sun thus only rotates every two Mercurian years. It appears heavily cratered like our moon indicating it has been geologically inactive for billions of years. It has almost no atmosphere to retain heat and surface temperatures range from -173°C at night to 427°C during the day, varying more than any other planet in our Solar System.",
            "Venus is the second planet from the Sun and has a rotational period of 243 days in the opposite direction. It is similar to Earth in its size, mass, and proximity to the Sun although radically different in other aspects. It has an extremely dense atmosphere of 96% carbon dioxide and is by far the hottest planet with a surface temperature of 462 °C. Venus is shrouded by an opaque layer of highly reflective clouds of sulfuric acid, preventing its surface from being seen from space in visible light. It may have had water oceans in the past, but these would have vaporized as the temperature rose due to a runaway greenhouse effect.",
            "Earth is our home planet and the third planet from the Sun. It is the only planet in our Solar System to have an atmosphere containing oxygen, oceans of liquid water and life. Earth has one moon, weighs 5.972×10^24kg, has a radius of 6'000 km and is 150 million km from the Sun. Earth's lithosphere is divided into several rigid tectonic plates that migrate across the surface over periods of many million years. It has a surface comprised of 70% water and 30% land.",
            "Mars is the fourth planet from the Sun and the second smallest planet in the Solar System. Iron oxide prevalent on its surface gives it a reddish appearance and its thin atmosphere has lead to many impact craters. The rotational period of Mars is similar to that of Earth. Mars is the site of Olympus Mons, the largest volcano, and second-highest known mountain in the Solar System, and of Valles Marineris, one of the largest canyons in the Solar System. The smooth Borealis basin in the northern hemisphere covers 40% of the planet and may be a giant impact feature. Mars has two moons, Phobos and Deimos, which are small and irregularly shaped.",
            "Jupiter is the fifth planet from the Sun and the largest in the Solar System. It is a giant planet with a mass one-thousandth that of the Sun, but two and a half times that of all the other planets in the Solar System combined. Jupiter and Saturn are gas giants; the other two giant planets, Uranus and Neptune are ice giants. Jupiter is primarily composed of hydrogen with a quarter of its mass being helium, though helium comprises only about a tenth of the number of molecules. Because of its rapid rotation, the planet's shape is that of an oblate spheroid. A prominent result is the Great Red Spot, a giant storm. Jupiter has at least 67 moons, the largest of these has a diameter greater than that of the planet Mercury.",
            "Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius about nine times that of Earth. Although it has only one-eighth the average density of Earth, with its larger volume Saturn is just over 95 times more massive. Saturn has a pale yellow hue due to ammonia crystals in its upper atmosphere. Saturn's magnetic field strength is around one-twentieth of Jupiter's. Wind speeds on Saturn can reach 1,800 km/h. Saturn has a prominent ring system that consists of nine continuous main rings and three discontinuous arcs and that is composed mostly of ice particles with a smaller amount of rocky debris and dust.",
            "Uranus is the seventh planet from the Sun. It has the third-largest planetary radius and fourth-largest planetary mass in the Solar System. Uranus is similar in composition to Neptune, and both have different bulk chemical composition from that of the larger gas giants Jupiter and Saturn. For this reason, scientists often classify Uranus and Neptune as \"ice giants\" to distinguish them from the gas giants. It is the coldest planetary atmosphere in the Solar System, with a minimum temperature of −224 °C, and has a complex, layered cloud structure with water thought to make up the lowest clouds and methane the uppermost layer of clouds. The interior of Uranus is mainly composed of ices and rock.",
            "Neptune is the eighth and farthest known planet from the Sun in the Solar System. In the Solar System, it is the fourth-largest planet by diameter, the third-most-massive planet, and the densest giant planet. Neptune is 17 times the mass of Earth and is slightly more massive than its near-twin Uranus, which is 15 times the mass of Earth and slightly larger than Neptune. Neptune orbits the Sun once every 164.8 years. The planet's southern hemisphere had a Great Dark Spot comparable to the Great Red Spot on Jupiter. These weather patterns are driven by the strongest sustained winds of any planet in the Solar System, with recorded wind speeds as high as 2,100 km/h.",
            "Pluto is a dwarf planet in the Kuiper belt, a ring of bodies beyond Neptune. It was the first Kuiper belt object to be discovered. Pluto was originally considered to be the ninth planet from the Sun. The term \"planet\" has been redefined since excluding Pluto. Like other Kuiper belt objects, Pluto is primarily made of ice and rock and is relatively small, about one-sixth the mass of the Moon and one-third its volume. It has a moderately eccentric and inclined orbit during which it ranges from 4.4–7.4 billion km from the Sun. This means that Pluto periodically comes closer to the Sun than Neptune, but a stable orbital resonance with Neptune prevents them from colliding. Light from the Sun takes about 5.5 hours to reach Pluto at its average distance.",
            "The Moon is an astronomical body that orbits planet Earth, being Earth's only permanent natural satellite. The average distance of the Moon from the Earth is 384,400 km  or 1.28 light-seconds. The Moon is thought to have formed about 4.5 billion years ago, not long after Earth. There are several hypotheses for its origin; the most widely accepted explanation is that the Moon formed from the debris left over after a giant impact between Earth and a Mars-sized body called Theia. The Moon's gravitational influence produces the ocean tides, body tides, and the slight lengthening of the day.",
            "Phobos is the innermost and larger of the two natural satellites of Mars, the other being Deimos. Phobos is a small, irregularly shaped object with a mean radius of 11 km. Phobos orbits 6,000 km from the Martian surface, closer to its primary body than any other known planetary moon, and completes an orbit in just 7 hours. Phobos is primarily composed of water ice and rocky material. Surface temperatures range from about −4 °C to −112 °C. The defining surface feature is the large impact crater, which takes up a substantial proportion of the moon's surface.",
            "Ganymede is the largest and most massive moon of Jupiter and in the Solar System. The ninth largest object in the Solar System, it is the largest without a substantial atmosphere. It has a diameter of 5,000 km and is 8% larger than the planet Mercury. Ganymede is primarily composed of water ice and rocky material. Possessing a metallic core, it has the lowest moment of inertia factor of any solid body in the Solar System and is the only moon known to have a magnetic field. Ganymede orbits Jupiter in roughly seven days. Ganymede has an internal ocean that may contain more water than all of Earth's oceans combined. Ganymede's magnetic field is probably created by convection within its liquid iron core.",
            "Io has the highest density of all the moons, and has the least amount of relative water of any known object in the Solar System. Io is composed of silicate rock surrounding a molten iron core. With over 400 active volcanoes, Io is the most geologically active object in the Solar System. This extreme geologic activity is the result of tidal heating from friction generated within Io's interior as it is pulled between Jupiter. Several volcanoes produce plumes of sulfur and sulfur dioxide that climb as high as 500 km above the surface. Io's surface is also dotted with more than 100 mountains that have been uplifted by extensive compression at the base of Io's silicate crust.",
            "Titan is the largest moon of Saturn. It is the only moon known to have a dense atmosphere, and the only object in space other than Earth where clear evidence of stable bodies of surface liquid has been found. It is the second-largest moon in the Solar System, after Jupiter's moon Ganymede. Titan is primarily composed of water ice and rocky material. Titan is primarily composed of water ice and rocky material. The atmosphere of Titan is largely nitrogen; minor components lead to the formation of methane and ethane clouds and nitrogen-rich organic smog. The climate creates surface features similar to those of Earth, and is dominated by seasonal weather patterns.",
            "Miranda is the smallest and innermost of Uranus's five round satellites. Like the other large moons of Uranus, Miranda orbits close to its planet's equatorial plane. Because Uranus orbits the Sun on its side, Miranda's orbit is perpendicular to the ecliptic and shares Uranus's extreme seasonal cycle. At just 470 km in diameter, Miranda is one of the smallest objects in the Solar System known to be spherical under its own gravity. Miranda has one of the most extreme and varied topographies of any object in the Solar System, including Verona Rupes, a 10 km high scarp that is the tallest cliff in the Solar System.",
            "Triton is the largest natural satellite of the planet Neptune. It is the only large moon in the Solar System with a retrograde orbit, an orbit in the opposite direction to its planet's rotation. At 2,700 kilometres in diameter, it is the seventh-largest moon in the Solar System. Triton is thought to have been a dwarf planet captured from the Kuiper belt. Triton has a surface of mostly frozen nitrogen, an icy mantle and a substantial core of rock and metal. The core makes up two-thirds of its total mass. Triton is one of the few moons in the Solar System known to be geologically active. As a consequence, part of its surface has geysers erupting sublimated nitrogen gas, contributing to a tenuous nitrogen atmosphere."
    };

    public ImageAdapterPlanet(Context c) {
        context = c;
    }

    @Override
    public int getCount() {
        return images.length;
    }
    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Typeface typeFaceBold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
        final float scale = context.getResources().getDisplayMetrics().density;

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        int la = (int) (150 * scale + 0.5f);
        int lb = (int) (50 * scale + 0.5f);
        int m = (int) (1 * scale + 0.5f);

        ViewGroup.LayoutParams llp = new ViewGroup.LayoutParams(new GridView.LayoutParams(la, la + lb));
        linearLayout.setLayoutParams(llp);
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.black));

        ImageView imageView = new ImageView(context);

        String uri = images[position];
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable res = context.getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);

        ViewGroup.LayoutParams ilp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, la);
        imageView.setLayoutParams(ilp);

        TextView textView = new TextView(context);
        LinearLayout.LayoutParams tlp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        tlp.leftMargin = m;
        tlp.rightMargin = m;
        tlp.bottomMargin = m;
        textView.setLayoutParams(tlp);
        textView.setTextSize(16);
        textView.setTextColor(ContextCompat.getColor(context, R.color.white));
        textView.setBackgroundResource(R.drawable.button);
        textView.setPadding(5, 0, 5, 0);
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(typeFaceBold);
        textView.setText(title[position]);

        linearLayout.addView(textView, 0);
        linearLayout.addView(imageView, 0);

        return linearLayout;
    }
}