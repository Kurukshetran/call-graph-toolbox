package com.ensoftcorp.open.cg.codemap;

import org.eclipse.core.runtime.IProgressMonitor;

import com.ensoftcorp.open.cg.analysis.ClassHierarchyAnalysis;
import com.ensoftcorp.open.cg.analysis.ClassicHybridTypeAnalysis;
import com.ensoftcorp.open.cg.analysis.ExceptionTypeAnalysis;
import com.ensoftcorp.open.cg.analysis.FieldTypeAnalysis;
import com.ensoftcorp.open.cg.analysis.HybridTypeAnalysis;
import com.ensoftcorp.open.cg.analysis.MethodTypeAnalysis;
import com.ensoftcorp.open.cg.analysis.RapidTypeAnalysis;
import com.ensoftcorp.open.cg.analysis.ReachabilityAnalysis;
import com.ensoftcorp.open.cg.analysis.ZeroControlFlowAnalysis;
import com.ensoftcorp.open.cg.preferences.CallGraphPreferences;
import com.ensoftcorp.open.commons.codemap.PrioritizedCodemapStage;
import com.ensoftcorp.open.pointsto.codemap.PointsToCodemapStage;

public class CallGraphCodemapStage extends PrioritizedCodemapStage {

	public static final String IDENTIFIER = "com.ensoftcorp.open.cg";
	
	@Override
	public String getDisplayName() {
		return "Call Graph Construction";
	}

	@Override
	public String getIdentifier() {
		return IDENTIFIER;
	}

	@Override
	public String[] getCodemapStageDependencies() {
		return new String[]{ PointsToCodemapStage.IDENTIFIER };
	}

	@Override
	public void performIndexing(IProgressMonitor monitor) {
		if(CallGraphPreferences.isReachabilityAnalysisEnabled()){
			ReachabilityAnalysis ra = ReachabilityAnalysis.getInstance();
			ra.run();
		}
		if(CallGraphPreferences.isClassHierarchyAnalysisEnabled()){
			ClassHierarchyAnalysis cha = ClassHierarchyAnalysis.getInstance();
			cha.run();
		}
		if(CallGraphPreferences.isRapidTypeAnalysisEnabled()){
			RapidTypeAnalysis rta = RapidTypeAnalysis.getInstance();
			rta.run();
		}
		if(CallGraphPreferences.isMethodTypeAnalysisEnabled()){
			MethodTypeAnalysis mta = MethodTypeAnalysis.getInstance();
			mta.run();
		}
		if(CallGraphPreferences.isFieldTypeAnalysisEnabled()){
			FieldTypeAnalysis fta = FieldTypeAnalysis.getInstance();
			fta.run();
		}
		if(CallGraphPreferences.isExceptionTypeAnalysisEnabled()){
			ExceptionTypeAnalysis eta = ExceptionTypeAnalysis.getInstance();
			eta.run();
		}
		if(CallGraphPreferences.isClassicHybridTypeAnalysisEnabled()){
			ClassicHybridTypeAnalysis xta = ClassicHybridTypeAnalysis.getInstance();
			xta.run();
		}
		if(CallGraphPreferences.isHybridTypeAnalysisEnabled()){
			HybridTypeAnalysis xta2 = HybridTypeAnalysis.getInstance();
			xta2.run();
		}
		if(CallGraphPreferences.isZeroCFAEnabled()){
			ZeroControlFlowAnalysis zeroCFA = ZeroControlFlowAnalysis.getInstance();
			zeroCFA.run();
		}
	}

}
