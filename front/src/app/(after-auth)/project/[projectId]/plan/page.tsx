import { StepTitleWithGuide } from '@/components/StepTitle/StepTitle';
import Change from "#/assets/icons/plan/change.svg"
import Image from 'next/image'
import * as styles from "@/containers/plan/styles/plan.css"
import PlanAfterAI from '@/containers/plan/components/afterAI'
import PlanBeforeAI from '@/containers/plan/components/beforeAI'
import Room from '@/app/(after-auth)/project/[projectId]/plan/Room'
import Live from '@/components/cursor/Live'
import { ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css';

function Plan () {
  return (
    <Room>
      <Live>
        <div>
          <StepTitleWithGuide stepNum={2} title="AI 기획서" desc="간단한 키워드를 입력해서 완성된 기획서를 얻을 수 있어요." guideTitle="기획서 작성 가이드" />
          <div className={styles.container}>
            <div className={styles.mainDivision}>

              {/* AI 생성 전 파트 */}
              <PlanBeforeAI/>

              {/* 일러스트 파트 */}
              <div className={styles.illustDivision}>
                <Image src={Change} alt="illust"/>
              </div>

              {/* AI 생성 파트 */}
              <PlanAfterAI/>

            </div>
          </div>
        </div>
        <ToastContainer/>
      </Live>
    </Room>
  );
}

export default Plan;
