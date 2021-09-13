package com.example.woohoo.base



import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment



abstract  class BaseFragment : Fragment() {


    /**
     * Used for add Fragment, set profile image etc...
     */
    var mActivity: BaseActivity? = null

    /**
     * Called from onCreateView () Function
     */
    public abstract fun getLayoutID():Int

    /**
     * Called from onViewCreated () Function
     */
    public abstract fun onCreateView();

    private var mContent: View? = null


    /**************************************  Fragment Lifecycle Methods  ************************************************************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as BaseActivity

    }



    private var rootView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(rootView == null) {
            rootView = inflater.inflate(getLayoutID(), container, false)
        } else{
            (rootView?.parent as? ViewGroup)?.removeView(rootView)
        }
        return rootView
    }

    var hasInitializedRootView = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContent = view
        if(!hasInitializedRootView) {
            hasInitializedRootView = true
            onCreateView()
        }
    }


    override fun onPause() {
        super.onPause()


    }

    override fun onStop() {
        super.onStop()

    }



    /**************************************  Fragment Lifecycle Methods  ************************************************************************************/


    /**
     * Immediately go back like click on back arrow icon or click on close button or click on cross icon
     */
    protected fun goBack() {
        activity?.onBackPressed()
    }

    /**************************************** Go Back *******************************************************************************************************/

    /********************************************* Show progress and hide progress ****************************************************************************************************/




}