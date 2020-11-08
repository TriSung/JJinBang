package com.tristar.jjinbang.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.tristar.jjinbang.R
import kotlinx.android.synthetic.main.search_option_fragment.*

class SearchOptionFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.search_option_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seekBar_size_min.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            }
            override fun onStartTrackingTouch(p0: SeekBar?) {  }
            override fun onStopTrackingTouch(p0: SeekBar?) {  }
        })

        seekBar_size_max.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            }
            override fun onStartTrackingTouch(p0: SeekBar?) {  }
            override fun onStopTrackingTouch(p0: SeekBar?) {  }
        })

        seekBar_price_deposit_min.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            }
            override fun onStartTrackingTouch(p0: SeekBar?) {  }
            override fun onStopTrackingTouch(p0: SeekBar?) {  }
        })

        seekBar_price_deposit_max.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            }
            override fun onStartTrackingTouch(p0: SeekBar?) {  }
            override fun onStopTrackingTouch(p0: SeekBar?) {  }
        })

        seekBar_price_month_min.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            }
            override fun onStartTrackingTouch(p0: SeekBar?) {  }
            override fun onStopTrackingTouch(p0: SeekBar?) {  }
        })

        seekBar_price_month_max.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            }
            override fun onStartTrackingTouch(p0: SeekBar?) {  }
            override fun onStopTrackingTouch(p0: SeekBar?) {  }
        })



    }
}
